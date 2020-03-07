package com.example.testapplication.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.example.testapplication.R;
import com.google.android.material.textfield.TextInputEditText;

public class InputCustomView extends TextInputEditText {
    private final int REQUIRED = 0;
    private final int UNREQUIRED = 1;
    private Context mContext;
    private int mTextType;
    private String mText;
    private String mHint;
    private TypedArray mTypeArray;
    private int mIsRequired = UNREQUIRED;

    public InputCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        mTypeArray = mContext.getTheme().obtainStyledAttributes(attrs, R.styleable.InputCustomView, 0, 0);
        try {
            mText = mTypeArray.getString(R.styleable.InputCustomView_customViewText);
            mHint = mTypeArray.getString(R.styleable.InputCustomView_customViewHint);
            mIsRequired = mTypeArray.getInt(R.styleable.InputCustomView_customViewIsRequired, UNREQUIRED);
            mTextType = mTypeArray.getInt(R.styleable.InputCustomView_customViewType, 0);

        } finally {
            mTypeArray.recycle();
        }
        if (mHint != null) {
            setHint(mHint);
        }

        switch (mTextType) {
            case 1:
                setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                break;
            case 2:
                setInputType(InputType.TYPE_CLASS_PHONE);
                break;
            case 3:
                setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;
        }
    }


    private boolean isMyTextValid(String text) {

        if (text != null) {
            if (mTextType == TextType.EMAIL.getValue()) {
                if (text.isEmpty() || !text.contains("@") || text.lastIndexOf(".") < text.lastIndexOf("@")) {
                    setError("ایمیل وارد شده صحیح نمیباشد!!!");
                    //throw new RuntimeException("no suitable email provided");
                    return false;
                } else
                    return true;
            }
            if (mTextType == TextType.NAME.getValue()) {
                if (text.matches(".*\\d.*")) {
                    setError("نام نباید شامل عدد باشد!!!");
//                    throw new RuntimeException("no suitable name provided");
                    return false;
                } else
                    return true;
            }
            if (mTextType == TextType.PHONE_NUMBER.getValue()) {
                if (text.length() != 11 || !text.startsWith("09")) {
                    setError("شماره وارد شده صحیح نمیباشد!!!");
//                    throw new RuntimeException("no suitable phoneNumber provided");
                    return false;
                } else
                    return true;
            }
        } else {
            if (mIsRequired == REQUIRED) {
                setError("فیلد خالی است!!!");
                // throw new RuntimeException("no text provided");
                return false;
            } else {
                return true;
            }
        }
        return false;
    }


    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);

        if (!isMyTextValid(getText().toString())) {
            focused = true;
        }
    }
}
