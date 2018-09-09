package com.zuba.carsharing.helper;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Looper;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.zuba.carsharing.R;
import com.zuba.carsharing.application.CarsharingApplication;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by bang.chang on 2018/6/13.
 */

public class ZubaHelper {
    private static Context mApplicationContext = CarsharingApplication.getAppContext();

    public static int sizeChange(double pxSize) {
        return (int) Math.ceil((pxSize));
    }

    /**
     * 取得資源檔顏色
     *
     * @param resourceId 資源檔Id
     * @return 資源檔所對應的顏色
     */
    public static int getColor(@ColorRes int resourceId) {
        return ContextCompat.getColor(mApplicationContext, resourceId);
    }

    /**
     * 取得資源檔文字
     *
     * @param resourceId 資源檔Id
     * @return 資源檔所對應的文字
     */
    public static String getString(@StringRes int resourceId) {
        //取得文字
        return mApplicationContext.getString(resourceId);
    }

    /**
     * 取得資源檔Dimens
     *
     * @param resourceId 資源檔Id
     * @return 資源檔所對應的數值與單位
     */
    public static int getDimen(@DimenRes int resourceId) {
        return (int) mApplicationContext.getResources().getDimension(resourceId);
    }

    /**
     * 取得DP
     *
     * @param dpSize 所需要的dpSize
     * @return 轉換過的DpSize
     */
    public static int getDP(float dpSize) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpSize, mApplicationContext.getResources().getDisplayMetrics());
    }

    /**
     * 取得記憶體資料
     *
     * @param context
     * @param name key
     */
    public static String getSharePreference(Context context, String name) {
        String cacheString = context.getSharedPreferences(name, MODE_PRIVATE).getString(name, "");

        try {
            if (cacheString != null && cacheString.equals("") == false) {
                cacheString = AESHelper.aesDecryptString(cacheString, getString(R.string.aes_key));
            }
        } catch (Exception e) {
        }

        return cacheString;
    }

    /**
     * 存入記憶體資料
     *
     * @param context
     * @param name key
     * @param value 資料
     */
    public static void putSharePreference(Context context, String name, String value) {
        try {
            if (value != null && value.equals("") == false) {
                value = AESHelper.aesEncryptString(value, getString(R.string.aes_key));
            } else {
                value = "";
            }
            context.getSharedPreferences(name, MODE_PRIVATE).edit().putString(name, value).commit();
        } catch (Exception e) {
        }
    }

    /**
     * 清除記憶體資料
     *
     * @param context
     * @param name key
     */
    public static void clearSharePreference(Context context, String name) {
        context.getSharedPreferences(name, 0).edit().clear().commit();
    }

    /**
     * 將圖片資源轉成軟引用
     *
     * @param resource bitmap
     */
    public static Bitmap getWeakReference(Bitmap resource) {
        WeakReference<Bitmap> bitmap = new WeakReference<>(resource);
        return bitmap.get();
    }

    /**
     * 隱藏Soft Keyboard
     *
     * @param activity
     */
    public static void hideKeyboard(Activity activity) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            View view = activity.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            activity.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
            );
        }
    }

    //取得目前時間
    public static String getNowDate() {
        Calendar calendar = Calendar.getInstance();
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
    }

    /**
     * 轉換RequestBody
     *
     * @param requestDataMap 參數
     */
    public static Map<String, RequestBody> generateRequestBody(Map<String, String> requestDataMap) {
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        for (String key : requestDataMap.keySet()) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),
                    requestDataMap.get(key) == null ? "" : requestDataMap.get(key));
            requestBodyMap.put(key, requestBody);
        }
        return requestBodyMap;
    }

    //取得裝置ID
    public static String getDeviceID() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? Build.getSerial() : Build.SERIAL;
    }

    //取得裝置Version
    public static String getVersion(Context context) {
        String version = "";
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = packageInfo.versionName;
        } catch (Exception e) {
        }
        return version;
    }

    //確認權限
    public static boolean checkPermission(Context context, String manifestPermission) {
        int permission = ActivityCompat.checkSelfPermission(context, manifestPermission);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }
}
