package com.zuba.carsharing.api.api_interface;

import com.zuba.carsharing.api.model.ApiResult;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

/**
 * Created by bang.chang on 2018/6/15.
 */

public interface ILoginService {
    //註冊
    @Multipart
    @POST("MemberRegister")
    Call<ApiResult> memberRegister(@PartMap Map<String, RequestBody> requestBodyMap);

    //註冊會員驗證碼
    @Multipart
    @POST("MemberRegisterVerify")
    Call<ApiResult> memberRegisterVerify(@PartMap Map<String, RequestBody> requestBodyMap);

    //會員密碼登入
    @Multipart
    @POST("MemberLogin")
    Call<ApiResult> memberLogin(@PartMap Map<String, RequestBody> requestBodyMap);

    //會員忘記密碼
    @Multipart
    @POST("MemberForget")
    Call<ApiResult> memberForget(@PartMap Map<String, RequestBody> requestBodyMap);

    //會員忘記密碼驗證
    @Multipart
    @POST("MemberForgetVerify")
    Call<ApiResult> memberForgetVerify(@PartMap Map<String, RequestBody> requestBodyMap);
}
