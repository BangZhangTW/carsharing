package com.zuba.carsharing.helper;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;

import com.zuba.carsharing.R;
import com.zuba.carsharing.application.CarsharingApplication;

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

    public static void clearSharePreference(Context context, String name) {
        context.getSharedPreferences(name, 0).edit().clear().commit();
    }
}
