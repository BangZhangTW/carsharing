package com.zuba.frame.view;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.zuba.carsharing.R;
import com.zuba.carsharing.enum_package.KeyboardTypeEnum;
import com.zuba.carsharing.helper.ZubaHelper;

/**
 * Created by bang.chang on 2018/7/11.
 */

public class ZubaRadiusEditView extends RelativeLayout {
    private EditText mEditText;

    public ZubaRadiusEditView(Context context) {
        super(context);

        createView();
    }

    public ZubaRadiusEditView(Context context, AttributeSet attrs) {
        super(context, attrs);

        createView();
    }

    private void createView() {
        setEditText();
    }

    private void setEditText() {
        mEditText = new EditText(getContext());
        mEditText.setBackgroundColor(ZubaHelper.getColor(R.color.transparent));
        mEditText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, ZubaHelper.getDimen(R.dimen.font_large));
        mEditText.setTextColor(ZubaHelper.getColor(R.color.original_black));
        mEditText.setHintTextColor(ZubaHelper.getColor(R.color.dark_black));
        mEditText.setSingleLine(true);
        mEditText.setPadding(ZubaHelper.getDimen(R.dimen.margin_on_both_sides), 0, ZubaHelper.getDimen(R.dimen.margin_on_both_sides), 0);

        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(CENTER_IN_PARENT);
        mEditText.setLayoutParams(layoutParams);

        this.addView(mEditText);
    }

    public EditText getEditText() {
        return mEditText;
    }

    public void setData(String content) {
        try {
            mEditText.setText(content);
        } catch (Exception e) {
        }
    }

    public void setColor(int color) {
        mEditText.setTextColor(ZubaHelper.getColor(color));
    }

    public void setHint(String hint) {
        mEditText.setHint(hint);
    }

    public String getValue() {
        return mEditText.getText().toString();
    }

    public void setPasswordMode() {
        mEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    public void setRadius(int radius, int color, int strokeColor) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(ZubaHelper.getDP(radius));
        gradientDrawable.setColor(ZubaHelper.getColor(color));
        if (strokeColor != -1) {
            gradientDrawable.setStroke(ZubaHelper.getDP(1), ZubaHelper.getColor(strokeColor));
        }

        this.setBackground(gradientDrawable);
    }

    public void setSearchMode(KeyboardTypeEnum keyboardType) {
        switch (keyboardType) {
            case SEARCH:
                mEditText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
                mEditText.setSingleLine(true);
                break;
        }
    }

    public void recycle() {
        try {
            mEditText = null;
            removeAllViews();
        } catch (Exception e) {
            removeAllViews();
        }
    }
}
