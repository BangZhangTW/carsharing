package com.zuba.carsharing.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.zuba.carsharing.R;
import com.zuba.carsharing.activity.base.BaseActivity;
import com.zuba.carsharing.api.base.ICallback;
import com.zuba.carsharing.api.model.ApiResult;
import com.zuba.carsharing.api.service.LoginService;
import com.zuba.carsharing.frame.layout.ForgetPasswordLayout;
import com.zuba.carsharing.helper.ValidationHelper;
import com.zuba.carsharing.helper.ZubaHelper;
import com.zuba.carsharing.util.Util;

import java.util.HashMap;
import java.util.Map;

import static android.view.View.VISIBLE;
import static com.zuba.carsharing.helper.ZubaHelper.generateRequestBody;

public class ForgetPasswordActivity extends BaseActivity {
    private ForgetPasswordLayout mForgetPasswordLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        mBackRelativeLayout.setVisibility(VISIBLE);
        mToolbarTitleTextView.setVisibility(VISIBLE);
        mToolbarTitleTextView.setText(ZubaHelper.getString(R.string.forget_password));

        mForgetPasswordLayout = (ForgetPasswordLayout) findViewById(R.id.forget_password_layout);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mForgetPasswordLayout.setLayoutParams(layoutParams);

        mForgetPasswordLayout.setVerifyCodeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Util.isEnableClick()) {
                    String phoneNumber = mForgetPasswordLayout.getPhoneNumber();
                    if (phoneNumber.isEmpty() == true) {
                        Toast.makeText(mContext, ZubaHelper.getString(R.string.please_enter_your_phone), Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (ValidationHelper.isMatchPhone(phoneNumber) == false) {
                        Toast.makeText(mContext, ZubaHelper.getString(R.string.please_input_correct_phone), Toast.LENGTH_LONG).show();
                        return;
                    }
                    memberForget(phoneNumber);
                }
            }
        });

        mForgetPasswordLayout.setSendOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Util.isEnableClick()) {
                    String phoneNumber = mForgetPasswordLayout.getPhoneNumber();
                    String verifyCode = mForgetPasswordLayout.getVerifyCode();
                    String newPassword = mForgetPasswordLayout.getNewPassword();
                    String newPasswordAgain = mForgetPasswordLayout.getNewPasswordAgain();
                    if (phoneNumber.isEmpty() == true) {
                        Toast.makeText(mContext, ZubaHelper.getString(R.string.please_enter_your_phone), Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (ValidationHelper.isMatchPhone(phoneNumber) == false) {
                        Toast.makeText(mContext, ZubaHelper.getString(R.string.please_input_correct_phone), Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (verifyCode.isEmpty() == true) {
                        Toast.makeText(mContext, ZubaHelper.getString(R.string.please_enter_verify_code), Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (newPassword.isEmpty() == true || newPasswordAgain.isEmpty() == true) {
                        Toast.makeText(mContext, ZubaHelper.getString(R.string.please_enter_new_password), Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (newPassword.equals(newPasswordAgain) == false) {
                        Toast.makeText(mContext, ZubaHelper.getString(R.string.please_enter_new_password_fail), Toast.LENGTH_LONG).show();
                        return;
                    }
                    memberForgetVerify(phoneNumber, verifyCode, newPassword);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mForgetPasswordLayout != null) {

        }
    }

    private void memberForget(String phoneNumber) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("AppId", ZubaHelper.getString(R.string.app_id));
        map.put("RequestTime", ZubaHelper.getNowDate());
        map.put("Mobile", phoneNumber);
        new LoginService(mContext).memberForget(generateRequestBody(map), new ICallback<ApiResult>() {
            @Override
            public void iSuccess(ApiResult data) {
                if (data != null) {
                    if (data.ReturnCode.equals("1") && data.ErrorMessage.isEmpty() == false) {
                        Toast.makeText(mContext, data.ErrorMessage, Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void iFail(String errorBody) {
                Toast.makeText(mContext, errorBody, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void memberForgetVerify(String phoneNumber, String verify, String newPassword) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("AppId", ZubaHelper.getString(R.string.app_id));
        map.put("RequestTime", ZubaHelper.getNowDate());
        map.put("Mobile", phoneNumber);
        map.put("VerifyCode", verify);
        map.put("NewPassword", newPassword);
        new LoginService(mContext).memberForgetVerify(generateRequestBody(map), new ICallback<ApiResult>() {
            @Override
            public void iSuccess(ApiResult data) {
                if (data != null) {
                    if (data.ReturnCode.equals("0")) {
                        finish();
                    } else if (data.ReturnCode.equals("1") && data.ErrorMessage.isEmpty() == false) {
                        Toast.makeText(mContext, data.ErrorMessage, Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void iFail(String errorBody) {
                Toast.makeText(mContext, errorBody, Toast.LENGTH_LONG).show();
            }
        });
    }
}
