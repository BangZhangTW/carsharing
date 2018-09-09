package com.zuba.carsharing.frame.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zuba.carsharing.R;
import com.zuba.carsharing.helper.ZubaHelper;
import com.zuba.carsharing.model.Record;
import com.zuba.carsharing.util.BitmapOptimize;
import com.zuba.carsharing.frame.view.RadiusIconView;
import com.zuba.carsharing.frame.view.ZubaRadiusEditView;

public class LoginLayout extends RelativeLayout {
    private int mRadius = 4;
    private int mMinHeight = 30;
    private int mMaxHeight = 45;

    private ImageView mBackgroundImageView;
    private ImageView mLogoImageView;
    private ZubaRadiusEditView mPhoneEditView;
    private ZubaRadiusEditView mPasswordEditView;
    private RadiusIconView mLoginRadiusIconView;
    private RelativeLayout mLineRelativeLayout;
    private TextView mForgetPasswordTextView;
    private TextView mAccountRegisterTextView;
    private RadiusIconView mFacebookRadiusIconView;
    private RadiusIconView mGoogleRadiusIconView;

    public LoginLayout(Context context) {
        super(context);

        createView();
    }

    public LoginLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        createView();
    }

    private void createView() {
        setBackgroundImageView();
        setLogoImageView();
        setPhoneEditView();
        setPasswordEditView();
        setLoginRadiusIconView();
        setLineRelativeLayout();
        setForgetPasswordTextView();
        setAccountRegisterTextView();
        setFacebookRadiusIconView();
        setGoogleRadiusIconView();
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
        mPhoneEditView.setHint(ZubaHelper.getString(R.string.please_enter_phone));
        mPhoneEditView.setRadius(ZubaHelper.getDP(mRadius), R.color.translucent_white, -1);

        LayoutParams layoutParams = new LayoutParams(ZubaHelper.sizeChange(((double) Record.mDisplay.width - ZubaHelper.getDimen(R.dimen.margin_on_both_sides) * 2)), ZubaHelper.getDP(mMinHeight));
        layoutParams.setMargins(0, ZubaHelper.getDP(40), 0, 0);
        layoutParams.addRule(BELOW, mLogoImageView.getId());
        layoutParams.addRule(CENTER_HORIZONTAL);
        mPhoneEditView.setLayoutParams(layoutParams);

        this.addView(mPhoneEditView);
    }

    private void setPasswordEditView() {
        mPasswordEditView = new ZubaRadiusEditView(getContext());
        mPasswordEditView.setId(View.generateViewId());
        mPasswordEditView.setHint(ZubaHelper.getString(R.string.please_enter_password));
        mPasswordEditView.setRadius(ZubaHelper.getDP(mRadius), R.color.translucent_white, -1);
        mPasswordEditView.setPasswordMode();

        LayoutParams layoutParams = new LayoutParams(ZubaHelper.sizeChange(((double) Record.mDisplay.width - ZubaHelper.getDimen(R.dimen.margin_on_both_sides) * 2)), ZubaHelper.getDP(mMinHeight));
        layoutParams.setMargins(0, ZubaHelper.getDP(15), 0, 0);
        layoutParams.addRule(BELOW, mPhoneEditView.getId());
        layoutParams.addRule(CENTER_HORIZONTAL);
        mPasswordEditView.setLayoutParams(layoutParams);

        this.addView(mPasswordEditView);
    }
    
    private void setLoginRadiusIconView() {
        mLoginRadiusIconView = new RadiusIconView(getContext());
        mLoginRadiusIconView.setId(View.generateViewId());
        mLoginRadiusIconView.setData(-1, ZubaHelper.getString(R.string.login), R.color.white, ZubaHelper.getDP(mRadius), R.color.light_blue, -1);
        mLoginRadiusIconView.setTextMode();

        LayoutParams layoutParams = new LayoutParams(ZubaHelper.sizeChange(((double) Record.mDisplay.width - ZubaHelper.getDimen(R.dimen.margin_on_both_sides) * 2)), ZubaHelper.getDP(mMaxHeight));
        layoutParams.setMargins(0, ZubaHelper.getDP(20), 0, 0);
        layoutParams.addRule(BELOW, mPasswordEditView.getId());
        layoutParams.addRule(CENTER_HORIZONTAL);
        mLoginRadiusIconView.setLayoutParams(layoutParams);

        this.addView(mLoginRadiusIconView);
    }

    private void setLineRelativeLayout() {
        mLineRelativeLayout = new RelativeLayout(getContext());
        mLineRelativeLayout.setId(View.generateViewId());
        mLineRelativeLayout.setBackgroundColor(ZubaHelper.getColor(R.color.white));

        LayoutParams layoutParams = new LayoutParams(ZubaHelper.getDP(1), ZubaHelper.getDP(30));
        layoutParams.setMargins(0, ZubaHelper.getDP(15), 0, 0);
        layoutParams.addRule(BELOW, mLoginRadiusIconView.getId());
        layoutParams.addRule(CENTER_HORIZONTAL);
        mLineRelativeLayout.setLayoutParams(layoutParams);

        this.addView(mLineRelativeLayout);
    }

    private void setForgetPasswordTextView() {
        mForgetPasswordTextView = new TextView(getContext());
        mForgetPasswordTextView.setId(View.generateViewId());
        mForgetPasswordTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, ZubaHelper.getDimen(R.dimen.font_large));
        mForgetPasswordTextView.setTextColor(ZubaHelper.getColor(R.color.white));
        mForgetPasswordTextView.setGravity(Gravity.CENTER);
        mForgetPasswordTextView.setText(ZubaHelper.getString(R.string.forget_password));

        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, ZubaHelper.getDP(30));
        layoutParams.setMargins(0, ZubaHelper.getDP(15), 0, 0);
        layoutParams.addRule(BELOW, mLoginRadiusIconView.getId());
        layoutParams.addRule(LEFT_OF, mLineRelativeLayout.getId());
        layoutParams.addRule(CENTER_HORIZONTAL);
        mForgetPasswordTextView.setLayoutParams(layoutParams);

        this.addView(mForgetPasswordTextView);
    }

    private void setAccountRegisterTextView() {
        mAccountRegisterTextView = new TextView(getContext());
        mAccountRegisterTextView.setId(View.generateViewId());
        mAccountRegisterTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, ZubaHelper.getDimen(R.dimen.font_large));
        mAccountRegisterTextView.setTextColor(ZubaHelper.getColor(R.color.white));
        mAccountRegisterTextView.setGravity(Gravity.CENTER);
        mAccountRegisterTextView.setText(ZubaHelper.getString(R.string.account_register));

        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, ZubaHelper.getDP(30));
        layoutParams.setMargins(0, ZubaHelper.getDP(15), 0, 0);
        layoutParams.addRule(BELOW, mLoginRadiusIconView.getId());
        layoutParams.addRule(RIGHT_OF, mLineRelativeLayout.getId());
        layoutParams.addRule(CENTER_HORIZONTAL);
        mAccountRegisterTextView.setLayoutParams(layoutParams);

        this.addView(mAccountRegisterTextView);
    }

    private void setFacebookRadiusIconView() {
        mFacebookRadiusIconView = new RadiusIconView(getContext());
        mFacebookRadiusIconView.setId(View.generateViewId());
        mFacebookRadiusIconView.setData(R.drawable.ic_facebook, ZubaHelper.getString(R.string.facebook_login), R.color.white, ZubaHelper.getDP(mRadius), R.color.fb_blue, -1);

        LayoutParams layoutParams = new LayoutParams(ZubaHelper.sizeChange(((double) Record.mDisplay.width - ZubaHelper.getDimen(R.dimen.margin_on_both_sides) * 2)), ZubaHelper.getDP(mMaxHeight));
        layoutParams.setMargins(0, ZubaHelper.getDP(15), 0, 0);
        layoutParams.addRule(BELOW, mLineRelativeLayout.getId());
        layoutParams.addRule(CENTER_HORIZONTAL);
        mFacebookRadiusIconView.setLayoutParams(layoutParams);

        this.addView(mFacebookRadiusIconView);
    }

    private void setGoogleRadiusIconView() {
        mGoogleRadiusIconView = new RadiusIconView(getContext());
        mGoogleRadiusIconView.setId(View.generateViewId());
        mGoogleRadiusIconView.setData(R.drawable.ic_google, ZubaHelper.getString(R.string.google_login), R.color.black, ZubaHelper.getDP(mRadius), R.color.white, -1);

        LayoutParams layoutParams = new LayoutParams(ZubaHelper.sizeChange(((double) Record.mDisplay.width - ZubaHelper.getDimen(R.dimen.margin_on_both_sides) * 2)), ZubaHelper.getDP(mMaxHeight));
        layoutParams.setMargins(0, ZubaHelper.getDP(15), 0, 0);
        layoutParams.addRule(BELOW, mFacebookRadiusIconView.getId());
        layoutParams.addRule(CENTER_HORIZONTAL);
        mGoogleRadiusIconView.setLayoutParams(layoutParams);

        this.addView(mGoogleRadiusIconView);
    }

    public String getPhoneNumber() {
        return mPhoneEditView.getValue();
    }

    public String getPassword() {
        return mPasswordEditView.getValue();
    }

    public void setLoginOnClickListener(OnClickListener listener) {
        mLoginRadiusIconView.setOnClickListener(listener);
    }

    public void setForgetPasswordOnClickListener(OnClickListener listener) {
        mForgetPasswordTextView.setOnClickListener(listener);
    }

    public void setAccountRegisterOnClickListener(OnClickListener listener) {
        mAccountRegisterTextView.setOnClickListener(listener);
    }
}
