package com.zuba.carsharing.api.base;

import android.content.Context;
import android.os.Build;

import com.zuba.carsharing.R;
import com.zuba.carsharing.helper.ZubaHelper;
import com.zuba.carsharing.util.NetWorkStatus;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bang.chang on 2018/6/15.
 */

public class HttpClient {
    private Context mContext;
    private String mApiUrl = "";
    private int mTime = 15;
    private static Retrofit mRetrofitAuthorization;

    public HttpClient(Context context) {
        mContext = context;
        mApiUrl = ZubaHelper.getString(R.string.api_url);
        if (NetWorkStatus.isConnect(context) == false) {
            //Toast
        }
    }

    public Retrofit authorizationService(boolean isAuthorization) {
        OkHttpClient.Builder httpClient = init(isAuthorization);
        mRetrofitAuthorization = (new Retrofit.Builder()
                .client(httpClient.build())
                .baseUrl(mApiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build());
        return mRetrofitAuthorization;
    }

    private OkHttpClient.Builder init(boolean isAuthorization) {
        OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder httpClient = client.newBuilder()
                .connectTimeout(mTime, TimeUnit.SECONDS)
                .writeTimeout(mTime, TimeUnit.SECONDS)
                .readTimeout(mTime, TimeUnit.SECONDS);
        if (isAuthorization == true) {
            //httpClient.addInterceptor(new TokenInterceptor(mContext.getApplicationContext()));
        }
        if (Build.VERSION.SDK_INT < 22) {
            httpClient.connectionSpecs(Collections.singletonList(setTLS1()));
        }
        return httpClient;
    }

    //設定TLS1
    private ConnectionSpec setTLS1() {
        return new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_0)
                .allEnabledCipherSuites()
                .build();
    }
}
