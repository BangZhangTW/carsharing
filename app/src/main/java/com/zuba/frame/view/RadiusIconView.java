package com.zuba.frame.view;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zuba.carsharing.R;
import com.zuba.carsharing.helper.ZubaHelper;

/**
 * Created by bang.chang on 2018/6/13.
 */

public class RadiusIconView extends RelativeLayout {
    private RelativeLayout mGroupRelativeLayout;
    private ImageView mIconImageView;
    private TextView mTitleText;

    public RadiusIconView(Context context) {
        super(context);

        createView();
    }

    public RadiusIconView(Context context, AttributeSet attrs) {
        super(context, attrs);

        createView();
    }

    private void createView() {
        setGroupRelativeLayout();
        setIconImageView();
        setTitleText();
    }

    private void setGroupRelativeLayout() {
        mGroupRelativeLayout = new RelativeLayout(getContext());
        mGroupRelativeLayout.setId(View.generateViewId());

        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(CENTER_IN_PARENT);
        mGroupRelativeLayout.setLayoutParams(layoutParams);

        this.addView(mGroupRelativeLayout);
    }

    private void setIconImageView() {
        mIconImageView = new ImageView(getContext());
        mIconImageView.setId(View.generateViewId());

        LayoutParams layoutParams = new LayoutParams(ZubaHelper.getDP(20), ZubaHelper.getDP(20));
        layoutParams.addRule(CENTER_VERTICAL);
        mIconImageView.setLayoutParams(layoutParams);

        mGroupRelativeLayout.addView(mIconImageView);
    }

    private void setTitleText() {
        mTitleText = new TextView(getContext());
        mTitleText.setId(View.generateViewId());
        mTitleText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, ZubaHelper.getDimen(R.dimen.font_large));

        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(ZubaHelper.getDP(30), 0, 0, 0);
        layoutParams.addRule(RIGHT_OF, mIconImageView.getId());
        mTitleText.setLayoutParams(layoutParams);

        mGroupRelativeLayout.addView(mTitleText);
    }

    public void setData(int icon, String title, int textColor, int radius, int color, int strokeColor) {
        try {
            if (icon != -1) {
                mIconImageView.setBackgroundResource(icon);
            }
            mTitleText.setText(title);
            mTitleText.setTextColor(ZubaHelper.getColor(textColor));
            this.setBackground(setRadius(radius, color, strokeColor));
        } catch (Exception e) {
        }
    }

    private GradientDrawable setRadius(int radius, int color, int strokeColor) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(ZubaHelper.getDP(radius));
        gradientDrawable.setColor(ZubaHelper.getColor(color));
        if (strokeColor != -1) {
            gradientDrawable.setStroke(ZubaHelper.getDP(1), ZubaHelper.getColor(strokeColor));
        }

        return gradientDrawable;
    }

    public void setTextMode() {
        try {
            mIconImageView.setVisibility(GONE);

            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(CENTER_IN_PARENT);
            mTitleText.setLayoutParams(layoutParams);
        } catch (Exception e) {
        }
    }
}
