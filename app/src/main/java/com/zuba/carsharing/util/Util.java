package com.zuba.carsharing.util;

/**
 * Created by bang.chang on 2018/6/20.
 */

public class Util {
    private static long mLastClickTime;
    private static int mFastTime = 250;
    private static int mTime = 500;

    public static boolean isEnableFastClick() {
        long time = System.currentTimeMillis();
        long timeD = time - mLastClickTime;
        if (0 < timeD && timeD < mFastTime) {
            return false;
        }
        mLastClickTime = time;
        mFastTime = 250;
        return true;
    }

    public static boolean isEnableClick() {
        long time = System.currentTimeMillis();
        long timeD = time - mLastClickTime;
        if (0 < timeD && timeD < mTime) {
            return false;
        }
        mLastClickTime = time;
        mTime = 500;
        return true;
    }

    public static boolean isEnableClick(int time) {
        mTime = time;
        return isEnableClick();
    }
}
