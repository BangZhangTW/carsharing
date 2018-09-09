package com.zuba.carsharing.helper;

import android.content.Context;

import com.google.gson.Gson;
import com.zuba.carsharing.model.PrefKey;

/**
 * Created by bang.chang on 2018/9/4.
 */

public class ZubaCacheHelper {

    public static void clearCacheByKey(Context context, String key) {
        try {
            ZubaHelper.clearSharePreference(context, key);
        } catch (Exception ex) {
        }
    }

    public static void setToken(Context context, String token) {
        try {
            if (token != null) {
                ZubaHelper.putSharePreference(context, PrefKey.PRF_TOKEN, token);
            }
        } catch (Exception ex) {
        }
    }

    public static String getToken(Context context) {
        String result = null;
        try {
            if (context != null) {
                result = ZubaHelper.getSharePreference(context, PrefKey.PRF_TOKEN);
            }
        } catch (Exception ex) {
        }

        return result;
    }
}
