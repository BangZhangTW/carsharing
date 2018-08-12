package com.zuba.carsharing.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.zuba.carsharing.R;
import com.zuba.carsharing.helper.ZubaHelper;
import com.zuba.carsharing.model.Display;
import com.zuba.carsharing.model.Record;

/**
 * Created by bang.chang on 2018/6/13.
 */

public class CarsharingApplication extends Application {
    private static Context mApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationContext = getApplicationContext();
        Record.mDisplay = getUIDisplay();
    }

    public static Context getAppContext() {
        return mApplicationContext;
    }

    public static Display getUIDisplay() {
        Resources mResources = mApplicationContext.getResources();
        Display display = new Display();
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        int resourceId = mResources.getIdentifier("status_bar_height", "dimen", "android");
        WindowManager windowManager = (WindowManager) mApplicationContext.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(mDisplayMetrics);//取得螢幕解析度
        }
        display.width = mDisplayMetrics.widthPixels;
        display.height = mDisplayMetrics.heightPixels - mResources.getDimensionPixelSize(resourceId);
        return display;
    }

    /**
     * 帶動畫關閉指定Context 的Activity
     */
    public static void exitActivity(Context context) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity) {
            ZubaHelper.hideKeyboard((Activity) context);
            ((Activity) context).finish();
            ((Activity) context).overridePendingTransition(R.anim.left_to_right_in, R.anim.left_to_right_out);
        }
    }
}
