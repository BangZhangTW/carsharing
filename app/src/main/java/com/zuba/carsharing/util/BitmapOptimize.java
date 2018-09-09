package com.zuba.carsharing.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

public class BitmapOptimize {
    BitmapFactory.Options bfOptions = new BitmapFactory.Options();

    public BitmapOptimize(int inSampleSiz) {
        bfOptions.inDither = false;//定義圖片減少佔用資源，防止OOM發生
        bfOptions.inPurgeable = true;
        bfOptions.inTempStorage = new byte[12 * 1024];
        //bfOptions.inPreferredConfig = Bitmap.Config.RGB_565;
        bfOptions.inSampleSize = inSampleSiz;
        bfOptions.inInputShareable = true;
    }

    public Bitmap getLocalBitmap(Context context, int resourceId) {
        InputStream inputStream = context.getResources().openRawResource(resourceId);

        return BitmapFactory.decodeStream(inputStream, null, bfOptions);
    }

    public Bitmap getSDBitmap(String photoPath) {

        return BitmapFactory.decodeFile(photoPath, bfOptions);
    }
}
