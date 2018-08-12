package com.zuba.carsharing.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.zuba.carsharing.R;
import com.zuba.carsharing.activity.base.BaseActivity;
import com.zuba.carsharing.helper.ZubaHelper;
import com.zuba.frame.layout.LoginLayout;

import static android.view.View.VISIBLE;

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
}
