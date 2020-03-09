package com.example.testapplication.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Patterns;

import com.example.testapplication.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;

public class InputCustomView extends TextInputEditText {
    private final int REQUIRED = 0;
    private final int UNREQUIRED = 1;

    private final int NAME = 1;
    private final int PHONE_NUMBER = 2;
    private final int EMAIL = 3;

    private Context mContext;
    private int mTextType;
    private String mHint;
    private TypedArray mTypeArray;
    private int mIsRequired;
    private boolean mIsValid;

    public InputCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        mTypeArray = mContext.getTheme().obtainStyledAttributes(attrs, R.styleable.InputCustomView, 0, 0);
        try {
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
            case NAME:
                setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                break;
            case PHONE_NUMBER:
                setInputType(InputType.TYPE_CLASS_PHONE);
                setMaxLengthForPhoneNumber();
                break;
            case EMAIL:
                setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;
        }
    }

    private void setMaxLengthForPhoneNumber() {
        int maxLength = 11;
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        setFilters(fArray);
    }

    public boolean isValid() {
        return mIsValid;
    }

    private void isMyTextValid(String text) {
        if (text != null && !text.isEmpty()) {
            switch (mTextType) {
                case NAME:
                    isNameValid(text);
                    break;
                case EMAIL:
                    isEmailValid(text);
                    break;
                case PHONE_NUMBER:
                    isPhoneNumberValid(text);
                    break;
                default:
            }
        }
    }

    private void isPhoneNumberValid(String text) {
        if (text.length() == 11 && text.startsWith("09")) {
            Matcher matcher = Patterns.PHONE.matcher(text);
            mIsValid = matcher.matches();
        } else {
            mIsValid = false;
        }

        if (!mIsValid) {
            setError("شماره وارد شده صحیح نمیباشد!!!");
        }
    }

    private void isNameValid(String text) {
        if (text.matches(".*\\d.*")) {
            setError("نام نباید شامل عدد باشد!!!");
            mIsValid = false;
        } else {
            mIsValid = true;
        }
    }

    private void isEmailValid(String text) {
        Matcher matcher = Patterns.EMAIL_ADDRESS.matcher(text);
        mIsValid = matcher.matches();
        if (!mIsValid)
            setError("ایمیل وارد شده صحیح نمیباشد!!!");

       /* if (!text.contains("@") ||
                text.lastIndexOf(".") < text.lastIndexOf("@") ||
                text.lastIndexOf(".") == text.length() - 1) {
            setError("ایمیل وارد شده صحیح نمیباشد!!!");
            mIsValid = false;
        } else {
            mIsValid = true;
        }*/
    }


    public boolean isInputEmpty() {
        String text = getText().toString();
        if (text.isEmpty()) {
            if (mIsRequired == REQUIRED) {
                setError("فیلد خالی است!!!");
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);

        String text = getText().toString();
        isMyTextValid(text);

        if (mIsValid) {
            super.onFocusChanged(false, direction, previouslyFocusedRect);
        } else {
            super.onFocusChanged(true, direction, previouslyFocusedRect);
        }
    }
}
