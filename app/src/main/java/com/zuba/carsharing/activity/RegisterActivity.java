package com.zuba.carsharing.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zuba.carsharing.R;
import com.zuba.carsharing.activity.base.BaseActivity;
import com.zuba.carsharing.api.base.ICallback;
import com.zuba.carsharing.api.model.ApiResult;
import com.zuba.carsharing.api.service.LoginService;
import com.zuba.carsharing.enum_package.ActivityTypeEnum;
import com.zuba.carsharing.frame.layout.RegisterLayout;
import com.zuba.carsharing.helper.ValidationHelper;
import com.zuba.carsharing.helper.ZubaHelper;
import com.zuba.carsharing.util.ActivityNavigation;
import com.zuba.carsharing.util.Util;

import java.util.HashMap;
import java.util.Map;

import static android.view.View.VISIBLE;
import static com.zuba.carsharing.helper.ZubaHelper.generateRequestBody;

public class RegisterActivity extends BaseActivity {
    private RegisterLayout mRegisterLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mBackRelativeLayout.setVisibility(VISIBLE);
        mToolbarTitleTextView.setVisibility(VISIBLE);
        mToolbarTitleTextView.setText(ZubaHelper.getString(R.string.register));
        mToolBarRightTextView.setVisibility(VISIBLE);
        mToolBarRightTextView.setText(ZubaHelper.getString(R.string.login));

        mToolBarRightTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Util.isEnableClick()) {
                    new ActivityNavigation().startActivity(mContext, ActivityTypeEnum.LOGIN);
                    finish();
                }
            }
        });

        mRegisterLayout = (RegisterLayout) findViewById(R.id.register_layout);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mRegisterLayout.setLayoutParams(layoutParams);

        mRegisterLayout.setVerifyCodeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Util.isEnableClick()) {
                    String phoneNumber = mRegisterLayout.getPhoneNumber();
                    if (phoneNumber.isEmpty() == true) {
                        Toast.makeText(mContext, ZubaHelper.getString(R.string.please_enter_your_phone), Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (ValidationHelper.isMatchPhone(phoneNumber) == false) {
                        Toast.makeText(mContext, ZubaHelper.getString(R.string.please_input_correct_phone), Toast.LENGTH_LONG).show();
                        return;
                    }
                    memberRegister(phoneNumber);
                }
            }
        });

        mRegisterLayout.setSendOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Util.isEnableClick()) {
                    String phoneNumber = mRegisterLayout.getPhoneNumber();
                    String verifyCode = mRegisterLayout.getVerifyCode();
                    if (phoneNumber.isEmpty() == true) {
                        Toast.makeText(mContext, ZubaHelper.getString(R.string.please_enter_your_phone), Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (verifyCode.isEmpty() == true) {
                        Toast.makeText(mContext, ZubaHelper.getString(R.string.please_enter_verify_code), Toast.LENGTH_LONG).show();
                        return;
                    }
                    memberRegisterVerify(phoneNumber, verifyCode);
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

        if (mRegisterLayout != null) {

        }
    }

    private void memberRegister(String phoneNumber) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("AppId", ZubaHelper.getString(R.string.app_id));
        map.put("RequestTime", ZubaHelper.getNowDate());
        map.put("Mobile", phoneNumber);
        new LoginService(mContext).memberRegister(generateRequestBody(map), new ICallback<ApiResult>() {
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

    private void memberRegisterVerify(String phoneNumber, String verifyCode) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("AppId", ZubaHelper.getString(R.string.app_id));
        map.put("RequestTime", ZubaHelper.getNowDate());
        map.put("Mobile", phoneNumber);
        map.put("VerifyCode", verifyCode);
        new LoginService(mContext).memberRegisterVerify(generateRequestBody(map), new ICallback<ApiResult>() {
            @Override
            public void iSuccess(ApiResult data) {
                if (data != null) {
                    if (data.ReturnCode.equals("0")) {
                        new ActivityNavigation().startActivity(mContext, ActivityTypeEnum.LOGIN);
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
