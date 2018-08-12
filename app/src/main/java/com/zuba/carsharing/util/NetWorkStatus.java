package com.zuba.carsharing.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by bang.chang on 2018/6/15.
 */

public class NetWorkStatus {

    /**
     * 檢查時否有網路連線
     *
     * @param context
     * @return 是否有網路連線
     */
    public static boolean isConnect(Context context) {
        if (getNetworkType(context) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 檢查是哪一種網路連線
     *
     * @param context
     * @return -1:未連接,0:Mobile ,1:WIFI,2: 2G ,3:有線網路
     */
    public static int getNetworkType(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return -1;
        } else {
            return networkInfo.getType();
        }
    }
}
