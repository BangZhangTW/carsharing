package com.zuba.carsharing.frame.layout;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zuba.carsharing.R;
import com.zuba.carsharing.frame.view.RadiusIconView;
import com.zuba.carsharing.frame.view.ZubaRadiusEditView;
import com.zuba.carsharing.helper.ZubaHelper;
import com.zuba.carsharing.model.Record;
import com.zuba.carsharing.util.BitmapOptimize;

/**
 * Created by bang.chang on 2018/8/15.
 */

public class RegisterLayout extends RelativeLayout {
    private int mRadius = 4;
    private int mMinHeight = 30;
    private int mMaxHeight = 45;

    private ImageView mBackgroundImageView;
    private ImageView mLogoImageView;
    private ZubaRadiusEditView mPhoneEditView;
    private RadiusIconView mVerifyCodeRadiusIconView;
    private ZubaRadiusEditView mVerifyCodeEditView;
    private ZubaRadiusEditView mInviteEditView;
    private RadiusIconView mSendRadiusIconView;
    private TextView mAgreeAccountPolicyTextView;

    public RegisterLayout(Context context) {
        super(context);

        createView();
    }

    public RegisterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        createView();
    }

    private void createView() {
        setBackgroundImageView();
        setLogoImageView();
        setPhoneEditView();
        setVerifyCodeRadiusIconView();
        setVerifyCodeEditView();
        setInviteEditView();
        setSendRadiusIconView();
        setAgreeAccountPolicyTextView();
    }

    private void setBackgroundImageView() {
        mBackgroundImageView = new ImageView(getContext());
        mBackgroundImageView.setId(View.generateViewId());
        mBackgroundImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mBackgroundImageView.setImageBitmap(ZubaHelper.getWeakReference(new BitmapOptimize(1).getLocalBitmap(getContext(), R.drawable.login_background)));

        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mBackgroundImageView.setLayoutParams(layoutParams);

        this.addView(mBackgroundImageView);
    }

    private void setLogoImageView() {
        mLogoImageView = new ImageView(getContext());
        mLogoImageView.setId(View.generateViewId());
        mLogoImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        mLogoImageView.setBackgroundResource(R.drawable.ic_logo);

        LayoutParams layoutParams = new LayoutParams(ZubaHelper.sizeChange(((double) Record.mDisplay.width / 100 * 25)), ZubaHelper.sizeChange(((double) (Record.mDisplay.width / 100 * 25) * 0.87)));
        layoutParams.setMargins(0, ZubaHelper.sizeChange(((double) Record.mDisplay.height / 100 * 6)), 0, 0);
        layoutParams.addRule(CENTER_HORIZONTAL);
        mLogoImageView.setLayoutParams(layoutParams);

        this.addView(mLogoImageView);
    }

    private void setPhoneEditView() {
        mPhoneEditView = new ZubaRadiusEditView(getContext());
        mPhoneEditView.setId(View.generateViewId());
        mPhoneEditView.setHint(ZubaHelper.getString(R.string.please_enter_your_phone));
        mPhoneEditView.setRadius(ZubaHelper.getDP(mRadius), R.color.translucent_white, -1);

        LayoutParams layoutParams = new LayoutParams(ZubaHelper.sizeChange(((double) (Record.mDisplay.width - ZubaHelper.getDimen(R.dimen.margin_on_both_sides) * 3) / 2)), ZubaHelper.getDP(mMinHeight));
        layoutParams.setMargins(ZubaHelper.getDimen(R.dimen.margin_on_both_sides), ZubaHelper.getDP(40), 0, 0);
        layoutParams.addRule(BELOW, mLogoImageView.getId());
        mPhoneEditView.setLayoutParams(layoutParams);

        this.addView(mPhoneEditView);
    }

    private void setVerifyCodeRadiusIconView() {
        mVerifyCodeRadiusIconView = new RadiusIconView(getContext());
        mVerifyCodeRadiusIconView.setId(View.generateViewId());
        mVerifyCodeRadiusIconView.setData(-1, ZubaHelper.getString(R.string.get_verify_code), R.color.white, ZubaHelper.getDP(mRadius), R.color.light_green, -1);
        mVerifyCodeRadiusIconView.setTextMode();

        LayoutParams layoutParams = new LayoutParams(ZubaHelper.sizeChange(((double) (Record.mDisplay.width - ZubaHelper.getDimen(R.dimen.margin_on_both_sides) * 3) / 2)), ZubaHelper.getDP(mMinHeight));
        layoutParams.setMargins(0, ZubaHelper.getDP(40), ZubaHelper.getDimen(R.dimen.margin_on_both_sides), 0);
        layoutParams.addRule(BELOW, mLogoImageView.getId());
        layoutParams.addRule(ALIGN_PARENT_RIGHT);
        mVerifyCodeRadiusIconView.setLayoutParams(layoutParams);

        this.addView(mVerifyCodeRadiusIconView);
    }

    private void setVerifyCodeEditView() {
        mVerifyCodeEditView = new ZubaRadiusEditView(getContext());
        mVerifyCodeEditView.setId(View.generateViewId());
        mVerifyCodeEditView.setHint(ZubaHelper.getString(R.string.please_enter_verify_code));
        mVerifyCodeEditView.setRadius(ZubaHelper.getDP(mRadius), R.color.translucent_white, -1);

        LayoutParams layoutParams = new LayoutParams(ZubaHelper.sizeChange(((double) Record.mDisplay.width - ZubaHelper.getDimen(R.dimen.margin_on_both_sides) * 2)), ZubaHelper.getDP(mMinHeight));
        layoutParams.setMargins(0, ZubaHelper.getDP(15), 0, 0);
        layoutParams.addRule(BELOW, mPhoneEditView.getId());
        layoutParams.addRule(CENTER_HORIZONTAL);
        mVerifyCodeEditView.setLayoutParams(layoutParams);

        this.addView(mVerifyCodeEditView);
    }

    private void setInviteEditView() {
        mInviteEditView = new ZubaRadiusEditView(getContext());
        mInviteEditView.setId(View.generateViewId());
        mInviteEditView.setHint(ZubaHelper.getString(R.string.please_enter_invite_code));
        mInviteEditView.setRadius(ZubaHelper.getDP(mRadius), R.color.translucent_white, -1);
        mInviteEditView.setPasswordMode();

        LayoutParams layoutParams = new LayoutParams(ZubaHelper.sizeChange(((double) Record.mDisplay.width - ZubaHelper.getDimen(R.dimen.margin_on_both_sides) * 2)), ZubaHelper.getDP(mMinHeight));
        layoutParams.setMargins(0, ZubaHelper.getDP(15), 0, 0);
        layoutParams.addRule(BELOW, mVerifyCodeEditView.getId());
        layoutParams.addRule(CENTER_HORIZONTAL);
        mInviteEditView.setLayoutParams(layoutParams);

        this.addView(mInviteEditView);
    }

    private void setSendRadiusIconView() {
        mSendRadiusIconView = new RadiusIconView(getContext());
        mSendRadiusIconView.setId(View.generateViewId());
        mSendRadiusIconView.setData(-1, ZubaHelper.getString(R.string.send), R.color.white, ZubaHelper.getDP(mRadius), R.color.light_blue, -1);
        mSendRadiusIconView.setTextMode();

        LayoutParams layoutParams = new LayoutParams(ZubaHelper.sizeChange(((double) Record.mDisplay.width - ZubaHelper.getDimen(R.dimen.margin_on_both_sides) * 2)), ZubaHelper.getDP(mMaxHeight));
        layoutParams.setMargins(0, ZubaHelper.getDP(20), 0, 0);
        layoutParams.addRule(BELOW, mInviteEditView.getId());
        layoutParams.addRule(CENTER_HORIZONTAL);
        mSendRadiusIconView.setLayoutParams(layoutParams);

        this.addView(mSendRadiusIconView);
    }

    private void setAgreeAccountPolicyTextView() {
        mAgreeAccountPolicyTextView = new TextView(getContext());
        mAgreeAccountPolicyTextView.setId(View.generateViewId());
        mAgreeAccountPolicyTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, ZubaHelper.getDimen(R.dimen.font_medium));
        mAgreeAccountPolicyTextView.setTextColor(ZubaHelper.getColor(R.color.white));
        mAgreeAccountPolicyTextView.setGravity(Gravity.CENTER);
        mAgreeAccountPolicyTextView.setText(Html.fromHtml(ZubaHelper.getString(R.string.agree_account_policy)));

        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, ZubaHelper.getDP(30), 0, 0);
        layoutParams.addRule(BELOW, mSendRadiusIconView.getId());
        layoutParams.addRule(CENTER_HORIZONTAL);
        mAgreeAccountPolicyTextView.setLayoutParams(layoutParams);

        this.addView(mAgreeAccountPolicyTextView);
    }

    public String getPhoneNumber() {
        return mPhoneEditView.getValue();
    }

    public String getVerifyCode() {
        return mVerifyCodeEditView.getValue();
    }

    public void setVerifyCodeOnClickListener(OnClickListener listener) {
        mVerifyCodeRadiusIconView.setOnClickListener(listener);
    }

    public void setSendOnClickListener(OnClickListener listener) {
        mSendRadiusIconView.setOnClickListener(listener);
    }
}
