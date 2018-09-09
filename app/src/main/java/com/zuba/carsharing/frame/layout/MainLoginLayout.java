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

public class MainLoginLayout extends RelativeLayout {
    private ImageView mBackgroundImageView;
    private ImageView mLogoImageView;
    private TextView mDescriptionTextView;
    private RelativeLayout mLineRelativeLayout;
    private TextView mLoginTextView;
    private TextView mAccountRegisterTextView;

    public MainLoginLayout(Context context) {
        super(context);

        createView();
    }

    public MainLoginLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        createView();
    }

    private void createView() {
        setBackgroundImageView();
        setLogoImageView();
        setDescriptionTextView();
        setLineRelativeLayout();
        setLoginTextView();
        setAccountRegisterTextView();
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

        LayoutParams layoutParams = new LayoutParams(ZubaHelper.sizeChange(((double) Record.mDisplay.width / 100 * 50)), ZubaHelper.sizeChange(((double) (Record.mDisplay.width / 100 * 50) * 0.87)));
        layoutParams.setMargins(0, ZubaHelper.sizeChange(((double) Record.mDisplay.height / 100 * 15)), 0 ,0);
        layoutParams.addRule(CENTER_HORIZONTAL);
        mLogoImageView.setLayoutParams(layoutParams);

        this.addView(mLogoImageView);
    }

    private void setDescriptionTextView() {
        mDescriptionTextView = new TextView(getContext());
        mDescriptionTextView.setId(View.generateViewId());
        mDescriptionTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, ZubaHelper.getDimen(R.dimen.font_medium));
        mDescriptionTextView.setTextColor(ZubaHelper.getColor(R.color.white));
        mDescriptionTextView.setMaxWidth(ZubaHelper.sizeChange(((double) Record.mDisplay.width / 100 * 80)));
        mDescriptionTextView.setGravity(Gravity.CENTER);
        mDescriptionTextView.setText(ZubaHelper.getString(R.string.login_description));

        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, ZubaHelper.sizeChange(((double) Record.mDisplay.height / 100 * 7)), 0, 0);
        layoutParams.addRule(BELOW, mLogoImageView.getId());
        layoutParams.addRule(CENTER_HORIZONTAL);
        mDescriptionTextView.setLayoutParams(layoutParams);

        this.addView(mDescriptionTextView);
    }

    private void setLineRelativeLayout() {
        mLineRelativeLayout = new RelativeLayout(getContext());
        mLineRelativeLayout.setId(View.generateViewId());
        mLineRelativeLayout.setBackgroundColor(ZubaHelper.getColor(R.color.white));

        LayoutParams layoutParams = new LayoutParams(ZubaHelper.getDP(1), ZubaHelper.getDP(30));
        layoutParams.setMargins(0, 0, 0, ZubaHelper.sizeChange(((double) Record.mDisplay.height / 100 * 20)));
        layoutParams.addRule(CENTER_HORIZONTAL);
        layoutParams.addRule(ALIGN_PARENT_BOTTOM);
        mLineRelativeLayout.setLayoutParams(layoutParams);

        this.addView(mLineRelativeLayout);
    }

    private void setLoginTextView() {
        mLoginTextView = new TextView(getContext());
        mLoginTextView.setId(View.generateViewId());
        mLoginTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, ZubaHelper.getDimen(R.dimen.font_large));
        mLoginTextView.setTextColor(ZubaHelper.getColor(R.color.white));
        mLoginTextView.setGravity(Gravity.CENTER);
        mLoginTextView.setText(ZubaHelper.getString(R.string.login));

        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, ZubaHelper.getDP(30));
        layoutParams.setMargins(0, 0, 0, ZubaHelper.sizeChange(((double) Record.mDisplay.height / 100 * 20)));
        layoutParams.addRule(LEFT_OF, mLineRelativeLayout.getId());
        layoutParams.addRule(ALIGN_PARENT_BOTTOM);
        mLoginTextView.setLayoutParams(layoutParams);

        this.addView(mLoginTextView);
    }

    private void setAccountRegisterTextView() {
        mAccountRegisterTextView = new TextView(getContext());
        mAccountRegisterTextView.setId(View.generateViewId());
        mAccountRegisterTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, ZubaHelper.getDimen(R.dimen.font_large));
        mAccountRegisterTextView.setTextColor(ZubaHelper.getColor(R.color.white));
        mAccountRegisterTextView.setGravity(Gravity.CENTER);
        mAccountRegisterTextView.setText(ZubaHelper.getString(R.string.account_register));

        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, ZubaHelper.getDP(30));
        layoutParams.setMargins(0, 0, 0, ZubaHelper.sizeChange(((double) Record.mDisplay.height / 100 * 20)));
        layoutParams.addRule(RIGHT_OF, mLineRelativeLayout.getId());
        layoutParams.addRule(ALIGN_PARENT_BOTTOM);
        mAccountRegisterTextView.setLayoutParams(layoutParams);

        this.addView(mAccountRegisterTextView);
    }

    public void setLoginOnClickListener(OnClickListener listener) {
        mLoginTextView.setOnClickListener(listener);
    }

    public void setAccountRegisterOnClickListener(OnClickListener listener) {
        mAccountRegisterTextView.setOnClickListener(listener);
    }

    public void recycle() {
        try {
            if (mBackgroundImageView != null) {
                mBackgroundImageView.setImageBitmap(null);
                mBackgroundImageView = null;
            }
            if (mLogoImageView != null) {
                mLogoImageView.setBackgroundResource(0);
                mLogoImageView = null;
            }
            if (mDescriptionTextView != null) {
                mDescriptionTextView.setText(null);
                mDescriptionTextView = null;
            }
            mLineRelativeLayout = null;
            if (mLoginTextView != null) {
                mLoginTextView.setText(null);
                mLoginTextView = null;
            }
            if (mAccountRegisterTextView != null) {
                mAccountRegisterTextView.setText(null);
                mAccountRegisterTextView = null;
            }
            removeAllViews();
        } catch (Exception e) {
            removeAllViews();
        }
    }
}
