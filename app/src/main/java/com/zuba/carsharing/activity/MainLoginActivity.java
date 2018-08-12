package com.zuba.carsharing.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.zuba.carsharing.R;
import com.zuba.carsharing.activity.base.BaseActivity;
import com.zuba.carsharing.enum_package.ActivityTypeEnum;
import com.zuba.carsharing.util.ActivityNavigation;
import com.zuba.carsharing.util.Util;
import com.zuba.frame.layout.MainLoginLayout;

import static android.view.View.GONE;

public class MainLoginActivity extends BaseActivity {
    private MainLoginLayout mMainLoginLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        mToolbar.setVisibility(GONE);

        mMainLoginLayout = (MainLoginLayout) findViewById(R.id.main_login_layout);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mMainLoginLayout.setLayoutParams(layoutParams);

        mMainLoginLayout.setLoginOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Util.isEnableClick()) {
                    new ActivityNavigation().startActivity(mContext, ActivityTypeEnum.LOGIN);
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

        if (mMainLoginLayout != null) {
            mMainLoginLayout.recycle();
        }
    }
}
