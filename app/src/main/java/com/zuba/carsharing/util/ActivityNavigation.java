package com.zuba.carsharing.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.zuba.carsharing.R;
import com.zuba.carsharing.activity.LoginActivity;
import com.zuba.carsharing.activity.MainLoginActivity;
import com.zuba.carsharing.enum_package.ActivityTypeEnum;

public class ActivityNavigation {

    public void startActivity(Context context, ActivityTypeEnum activityTypeEnum) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(context, getClass(activityTypeEnum));
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.right_to_left_in, R.anim.right_to_left_out);
    }

    private Class getClass(ActivityTypeEnum activityTypeEnum) {
        switch (activityTypeEnum) {
            case MAIN_LOGIN:
                return MainLoginActivity.class;
            case LOGIN:
                return LoginActivity.class;
            default:
                return null;
        }
    }
}
