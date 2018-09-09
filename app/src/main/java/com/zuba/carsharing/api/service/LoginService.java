package com.zuba.carsharing.api.service;

import android.content.Context;

import com.zuba.carsharing.api.api_interface.ILoginService;
import com.zuba.carsharing.api.base.ApiService;
import com.zuba.carsharing.api.base.HttpClient;
import com.zuba.carsharing.api.base.ICallback;
import com.zuba.carsharing.api.model.ApiResult;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Created by bang.chang on 2018/6/15.
 */

public class LoginService extends ApiService {
    private ILoginService mILoginService;
    private Context mContext;
    private HttpClient mHttpClient;

    public LoginService(Context context) {
        mContext = context;
        mHttpClient = new HttpClient(context);
        mILoginService = mHttpClient.authorizationService(false).create(ILoginService.class);
    }

    public void memberRegister(Map<String, RequestBody> requestBodyMap, ICallback iCallback) {
        Call<ApiResult> call = mILoginService.memberRegister(requestBodyMap);
        CallApi(mContext, call, iCallback);
    }

    public void memberRegisterVerify(Map<String, RequestBody> requestBodyMap, ICallback iCallback) {
        Call<ApiResult> call = mILoginService.memberRegisterVerify(requestBodyMap);
        CallApi(mContext, call, iCallback);
    }

    public void memberLogin(Map<String, RequestBody> requestBodyMap, ICallback iCallback) {
        Call<ApiResult> call = mILoginService.memberLogin(requestBodyMap);
        CallApi(mContext, call, iCallback);
    }

    public void memberForget(Map<String, RequestBody> requestBodyMap, ICallback iCallback) {
        Call<ApiResult> call = mILoginService.memberForget(requestBodyMap);
        CallApi(mContext, call, iCallback);
    }

    public void memberForgetVerify(Map<String, RequestBody> requestBodyMap, ICallback iCallback) {
        Call<ApiResult> call = mILoginService.memberForgetVerify(requestBodyMap);
        CallApi(mContext, call, iCallback);
    }
}
