package com.zuba.carsharing.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zuba.carsharing.R;
import com.zuba.carsharing.helper.ZubaHelper;
import com.zuba.carsharing.util.Util;

public class BaseActivity extends AppCompatActivity {
    protected Context mContext;

    protected Toolbar mToolbar;
    protected RelativeLayout mBackRelativeLayout;
    protected ImageView mBackImageView;
    protected TextView mToolBarRightTextView;
    protected RelativeLayout mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        mContext = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        if (R.layout.activity_base == layoutResID) {
            super.setContentView(R.layout.activity_base);
        } else if (R.layout.activity_base != layoutResID) {
            View view = LayoutInflater.from(this).inflate(layoutResID, null);
            setToolbar();
            setBackImageView();
            setToolbarRightTextView();
            setContentView();
            mContentView.addView(view, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        }
    }

    private void setToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setContentInsetsAbsolute(0, 0);
        mToolbar.setBackgroundResource(R.drawable.design_bottom_line_border);
        setSupportActionBar(mToolbar);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, ZubaHelper.getDimen(R.dimen.tool_bar_height));
        mToolbar.setLayoutParams(layoutParams);
    }

    private void setBackImageView() {
        mBackRelativeLayout = (RelativeLayout) findViewById(R.id.back_relative_layout);
        mBackRelativeLayout.setVisibility(View.GONE);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ZubaHelper.getDP(45), RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        mBackRelativeLayout.setLayoutParams(layoutParams);

        mBackRelativeLayout.setOnClickListener(backOnClickListener);

        mBackImageView = (ImageView) findViewById(R.id.back_image_view);
        mBackImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        mBackImageView.setImageResource(R.drawable.ic_left);
        mBackImageView.setClickable(true);

        RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(ZubaHelper.getDP(22), ZubaHelper.getDP(22));
        relativeLayoutParams.setMargins(ZubaHelper.getDimen(R.dimen.margin_on_both_sides), 0, 0, 0);
        relativeLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        mBackImageView.setLayoutParams(relativeLayoutParams);

        mBackImageView.setOnClickListener(backOnClickListener);
    }

    private void setToolbarRightTextView() {
        mToolBarRightTextView = (TextView) findViewById(R.id.toolbar_right_text_view);
        mToolBarRightTextView.setTextSize(ZubaHelper.getDimen(R.dimen.font_large));
        mToolBarRightTextView.setTextColor(ZubaHelper.getColor(R.color.black));
        mToolBarRightTextView.setGravity(Gravity.CENTER);
        mToolBarRightTextView.setVisibility(View.GONE);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0, 0, ZubaHelper.getDimen(R.dimen.margin_on_both_sides), 0);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        mToolBarRightTextView.setLayoutParams(layoutParams);
    }

    private void setContentView() {
        mContentView = (RelativeLayout) findViewById(R.id.content_view);
    }

    private View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (Util.isEnableClick()) {
                finish();
            }
        }
    };
}
