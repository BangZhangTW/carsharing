package com.zuba.carsharing.api.base;

import android.content.Context;

import com.google.gson.Gson;
import com.zuba.carsharing.api.model.ErrorMessage;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bang.chang on 2018/6/15.
 */

public abstract class ApiService {
    private Context mContext;
    private Callback mCallback;
    private ICallback mICallback;

    protected void CallApi(Context context, Call<?> call, ICallback iCallback) {
        mContext = context;
        mICallback = iCallback;
        mCallback = getCallBack();
        call.enqueue(mCallback);
    }

    protected Callback getCallBack() {
        mCallback = new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                try {
                    if (response.code() == 200) {
                        mICallback.iSuccess(response.body());
                    } else {
                        ResponseBody errorBody = response.errorBody();
                        if (errorBody != null) {
                            String errorBodyStr = errorBody.string();
                            mICallback.iFail(errorBodyStr);
                        } else {
                            mICallback.iFail(new Gson().toJson(setErrorMessage(500)));
                        }
                    }

                } catch (Exception ex) {
                    ErrorMessage errorMessage = setErrorMessage(500);
                    mICallback.iFail(new Gson().toJson(errorMessage));
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                mICallback.iFail(new Gson().toJson(setErrorMessage(408)));
            }
        };
        return mCallback;
    }

    private ErrorMessage setErrorMessage(int statusCode) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.code = Integer.toString(statusCode);
        switch (statusCode) {
            case 500:
                //errorMessage.message = LativHelper.getString(R.string.service_error);
                break;
            case 408:
                //errorMessage.message = LativHelper.getString(R.string.connection_error);
                break;
            default:
                //errorMessage.message = LativHelper.getString(R.string.service_error);
                break;
        }
        return errorMessage;
    }
}
