package com.zuba.carsharing.frame.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zuba.carsharing.R;
import com.zuba.carsharing.frame.view.RadiusIconView;
import com.zuba.carsharing.frame.view.ZubaRadiusEditView;
import com.zuba.carsharing.helper.ZubaHelper;
import com.zuba.carsharing.model.Record;
import com.zuba.carsharing.util.BitmapOptimize;

/**
 * Created by bang.chang on 2018/8/15.
 */

public class ForgetPasswordLayout extends RelativeLayout {
    private int mRadius = 4;
    private int mMinHeight = 30;
    private int mMaxHeight = 45;

    private ImageView mBackgroundImageView;
    private ImageView mLogoImageView;
    private ZubaRadiusEditView mPhoneEditView;
    private RadiusIconView mVerifyCodeRadiusIconView;
    private ZubaRadiusEditView mVerifyCodeEditView;
    private ZubaRadiusEditView mNewPasswordEditView;
    private ZubaRadiusEditView mNewPasswordAgainEditView;
    private RadiusIconView mClearRadiusIconView;
    private RadiusIconView mSendRadiusIconView;

    public ForgetPasswordLayout(Context context) {
        super(context);

        createView();
    }

    public ForgetPasswordLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        createView();
    }

    private void createView() {
        setBackgroundImageView();
        setLogoImageView();
        setPhoneEditView();
        setVerifyCodeRadiusIconView();
        setVerifyCodeEditView();
        setNewPasswordEditView();
        setNewPasswordAgainEditView();
        setClearRadiusIconView();
        setSendRadiusIconView();
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

    private void setNewPasswordEditView() {
        mNewPasswordEditView = new ZubaRadiusEditView(getContext());
        mNewPasswordEditView.setId(View.generateViewId());
        mNewPasswordEditView.setHint(ZubaHelper.getString(R.string.please_enter_new_password));
        mNewPasswordEditView.setRadius(ZubaHelper.getDP(mRadius), R.color.translucent_white, -1);
        mNewPasswordEditView.setPasswordMode();

        LayoutParams layoutParams = new LayoutParams(ZubaHelper.sizeChange(((double) Record.mDisplay.width - ZubaHelper.getDimen(R.dimen.margin_on_both_sides) * 2)), ZubaHelper.getDP(mMinHeight));
        layoutParams.setMargins(0, ZubaHelper.getDP(15), 0, 0);
        layoutParams.addRule(BELOW, mVerifyCodeEditView.getId());
        layoutParams.addRule(CENTER_HORIZONTAL);
        mNewPasswordEditView.setLayoutParams(layoutParams);

        this.addView(mNewPasswordEditView);
    }

    private void setNewPasswordAgainEditView() {
        mNewPasswordAgainEditView = new ZubaRadiusEditView(getContext());
        mNewPasswordAgainEditView.setId(View.generateViewId());
        mNewPasswordAgainEditView.setHint(ZubaHelper.getString(R.string.please_enter_new_password_again));
        mNewPasswordAgainEditView.setRadius(ZubaHelper.getDP(mRadius), R.color.translucent_white, -1);
        mNewPasswordAgainEditView.setPasswordMode();

        LayoutParams layoutParams = new LayoutParams(ZubaHelper.sizeChange(((double) Record.mDisplay.width - ZubaHelper.getDimen(R.dimen.margin_on_both_sides) * 2)), ZubaHelper.getDP(mMinHeight));
        layoutParams.setMargins(0, ZubaHelper.getDP(15), 0, 0);
        layoutParams.addRule(BELOW, mNewPasswordEditView.getId());
        layoutParams.addRule(CENTER_HORIZONTAL);
        mNewPasswordAgainEditView.setLayoutParams(layoutParams);

        this.addView(mNewPasswordAgainEditView);
    }

    private void setClearRadiusIconView() {
        mClearRadiusIconView = new RadiusIconView(getContext());
        mClearRadiusIconView.setId(View.generateViewId());
        mClearRadiusIconView.setData(-1, ZubaHelper.getString(R.string.clear), R.color.white, ZubaHelper.getDP(mRadius), R.color.deep_gray, -1);
        mClearRadiusIconView.setTextMode();

        LayoutParams layoutParams = new LayoutParams(ZubaHelper.sizeChange(((double) (Record.mDisplay.width - ZubaHelper.getDimen(R.dimen.margin_on_both_sides) * 3) / 2)), ZubaHelper.getDP(mMaxHeight));
        layoutParams.setMargins(ZubaHelper.getDimen(R.dimen.margin_on_both_sides), ZubaHelper.getDP(20), 0, 0);
        layoutParams.addRule(BELOW, mNewPasswordAgainEditView.getId());
        mClearRadiusIconView.setLayoutParams(layoutParams);

        this.addView(mClearRadiusIconView);
    }

    private void setSendRadiusIconView() {
        mSendRadiusIconView = new RadiusIconView(getContext());
        mSendRadiusIconView.setId(View.generateViewId());
        mSendRadiusIconView.setData(-1, ZubaHelper.getString(R.string.send), R.color.white, ZubaHelper.getDP(mRadius), R.color.light_blue, -1);
        mSendRadiusIconView.setTextMode();

        LayoutParams layoutParams = new LayoutParams(ZubaHelper.sizeChange(((double) (Record.mDisplay.width - ZubaHelper.getDimen(R.dimen.margin_on_both_sides) * 3) / 2)), ZubaHelper.getDP(mMaxHeight));
        layoutParams.setMargins(0, ZubaHelper.getDP(20), ZubaHelper.getDimen(R.dimen.margin_on_both_sides), 0);
        layoutParams.addRule(BELOW, mNewPasswordAgainEditView.getId());
        layoutParams.addRule(ALIGN_PARENT_RIGHT);
        mSendRadiusIconView.setLayoutParams(layoutParams);

        this.addView(mSendRadiusIconView);
    }

    public void setVerifyCodeOnClickListener(OnClickListener listener) {
        mVerifyCodeRadiusIconView.setOnClickListener(listener);
    }

    public void setClearOnClickListener(OnClickListener listener) {
        mClearRadiusIconView.setOnClickListener(listener);
    }

    public void setSendOnClickListener(OnClickListener listener) {
        mSendRadiusIconView.setOnClickListener(listener);
    }

    public String getPhoneNumber() {
        return mPhoneEditView.getValue();
    }

    public String getVerifyCode() {
        return mVerifyCodeEditView.getValue();
    }

    public String getNewPassword() {
        return mNewPasswordEditView.getValue();
    }

    public String getNewPasswordAgain() {
        return mNewPasswordAgainEditView.getValue();
    }
}
