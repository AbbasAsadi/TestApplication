package com.example.testapplication.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;


import androidx.annotation.Nullable;

import com.example.testapplication.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class InputCustomView extends TextInputLayout {
    private Context mContext;
    private TextInputEditText mEditText;

    private int mTextType;
    private String mText;
    private String mHint;
    private TypedArray mTypeArray;
    private boolean mIsRequired;

    public InputCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        mTypeArray = mContext.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0);
        mEditText = findViewById(R.id.custom_text_input);
        try {
            mText = mTypeArray.getString(R.styleable.CustomView_customViewText);
            mHint = mTypeArray.getString(R.styleable.CustomView_customViewHint);
            mIsRequired = mTypeArray.getBoolean(R.styleable.CustomView_customViewIsRequired, false);
            mTextType = mTypeArray.getInt(R.styleable.CustomView_customViewType, 0);

        } finally {
            mTypeArray.recycle();
        }
        if (isMyTextValid() && isMyHintValid()) {
            init(mText, mHint);
        } else {
            throw new RuntimeException("input data is wrong");
        }

    }

    private boolean isMyHintValid() {
        return mHint != null;
    }

    private boolean isMyTextValid() {
        if (mText != null) {
            if (mTextType == TextType.EMAIL.getValue()) {
                if (mText.isEmpty() || !mText.contains("@") || mText.lastIndexOf(".") < mText.lastIndexOf("@")) {
                    mEditText.setError("ایمیل وارد شده صحیح نمیباشد!!!");
                    //throw new RuntimeException("no suitable email provided");
                    return false;
                } else
                    return true;
            }
            if (mTextType == TextType.NAME.getValue()) {
                if (mText.matches(".*\\d.*")) {
                    mEditText.setError("نام نباید شامل عدد باشد!!!");
//                    throw new RuntimeException("no suitable name provided");
                    return false;
                } else
                    return true;
            }
            if (mTextType == TextType.PHONE_NUMBER.getValue()) {
                if (mText.length() != 11 || !mText.startsWith("09")) {
                    mEditText.setError("شماره وارد شده صحیح نمیباشد!!!");
//                    throw new RuntimeException("no suitable phoneNumber provided");
                    return false;
                } else
                    return true;
            }
        } else {
            if (mIsRequired) {
                mEditText.setError("فیلد خالی است!!!");
                // throw new RuntimeException("no text provided");
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    private void init(String text, String hint) {
        mEditText.setHint(text);
        mEditText.setText(hint);
    }
}
