package com.zuba.carsharing.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.zuba.carsharing.R;
import com.zuba.carsharing.activity.base.BaseActivity;
import com.zuba.carsharing.api.base.ICallback;
import com.zuba.carsharing.api.model.ApiResult;
import com.zuba.carsharing.api.service.LoginService;
import com.zuba.carsharing.enum_package.ActivityTypeEnum;
import com.zuba.carsharing.frame.layout.LoginLayout;
import com.zuba.carsharing.helper.ValidationHelper;
import com.zuba.carsharing.helper.ZubaCacheHelper;
import com.zuba.carsharing.helper.ZubaHelper;
import com.zuba.carsharing.util.ActivityNavigation;
import com.zuba.carsharing.util.Util;

import java.util.HashMap;
import java.util.Map;

import static android.view.View.VISIBLE;
import static com.zuba.carsharing.helper.ZubaHelper.generateRequestBody;

public class LoginActivity extends BaseActivity {
    private LoginLayout mLoginLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mBackRelativeLayout.setVisibility(VISIBLE);
        mToolbarTitleTextView.setVisibility(VISIBLE);
        mToolbarTitleTextView.setText(ZubaHelper.getString(R.string.login));

        mLoginLayout = (LoginLayout) findViewById(R.id.login_layout);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mLoginLayout.setLayoutParams(layoutParams);

        mLoginLayout.setLoginOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Util.isEnableClick()) {
                    String phoneNumber = mLoginLayout.getPhoneNumber();
                    String password = mLoginLayout.getPassword();
                    if (phoneNumber.isEmpty() == true) {
                        Toast.makeText(mContext, ZubaHelper.getString(R.string.please_enter_your_phone), Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (ValidationHelper.isMatchPhone(phoneNumber) == false) {
                        Toast.makeText(mContext, ZubaHelper.getString(R.string.please_input_correct_phone), Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (password.isEmpty() == true) {
                        Toast.makeText(mContext, ZubaHelper.getString(R.string.please_enter_password), Toast.LENGTH_LONG).show();
                        return;
                    }
                    permission(phoneNumber, password);
                }
            }
        });

        mLoginLayout.setForgetPasswordOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Util.isEnableClick()) {
                    new ActivityNavigation().startActivity(mContext, ActivityTypeEnum.FORGET_PASSWORD);
                }
            }
        });

        mLoginLayout.setAccountRegisterOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Util.isEnableClick()) {
                    new ActivityNavigation().startActivity(mContext, ActivityTypeEnum.REGISTER);
                    finish();
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

        if (mLoginLayout != null) {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            if (ZubaHelper.checkPermission(mContext, Manifest.permission.READ_PHONE_STATE) == true) {
                if (mLoginLayout != null) {
                    String phoneNumber = mLoginLayout.getPhoneNumber();
                    String password = mLoginLayout.getPassword();
                    memberLogin(phoneNumber, password);
                }
            } else {
                Toast.makeText(mContext, ZubaHelper.getString(R.string.agree_read_phone_state), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void permission(String phoneNumber, String password) {
        if (ZubaHelper.checkPermission(mContext, Manifest.permission.READ_PHONE_STATE) == true) {
            memberLogin(phoneNumber, password);
        } else {
            ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.READ_PHONE_STATE}, 0);
        }
    }

    private void memberLogin(String phoneNumber, String password) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("AppId", ZubaHelper.getString(R.string.app_id));
        map.put("RequestTime", ZubaHelper.getNowDate());
        map.put("Mobile", phoneNumber);
        map.put("Password", password);
        map.put("DeviceId", ZubaHelper.getDeviceID());
        map.put("Version", ZubaHelper.getVersion(mContext));
        new LoginService(mContext).memberLogin(generateRequestBody(map), new ICallback<ApiResult<String>>() {
            @Override
            public void iSuccess(ApiResult<String> data) {
                if (data != null) {
                    if (data.ReturnCode.equals("0")) {
                        ZubaCacheHelper.setToken(mContext, data.ReturnData);
                        new ActivityNavigation().startActivity(mContext, ActivityTypeEnum.HOME);
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
