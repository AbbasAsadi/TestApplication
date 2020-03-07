package com.example.testapplication.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;

import com.example.testapplication.R;
import com.google.android.material.textfield.TextInputEditText;

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
    private boolean isValid = false;

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
                int maxLength = 11;
                InputFilter[] fArray = new InputFilter[1];
                fArray[0] = new InputFilter.LengthFilter(maxLength);
                setFilters(fArray);
                break;
            case EMAIL:
                setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;
        }
    }

    public boolean isValid() {
        return isValid;
    }


    private boolean isMyTextValid(String text) {
        if (text != null && text.isEmpty()) {
            if (mIsRequired == REQUIRED) {
                setError("فیلد خالی است!!!");
                isValid = false;
            } else {
                isValid = true;
            }
        } else {
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
        } /*else {
           setError("null");
        }*/
        return isValid;
    }

    private void isPhoneNumberValid(String text) {
        if (text.length() != 11 || !text.startsWith("09")) {
            setError("شماره وارد شده صحیح نمیباشد!!!");
            isValid = false;
        } else {
            isValid = true;
        }
    }

    private void isNameValid(String text) {
        if (text.matches(".*\\d.*")) {
            setError("نام نباید شامل عدد باشد!!!");
            isValid = false;
        } else {
            isValid = true;
        }
    }

    private void isEmailValid(String text) {
        if (text.isEmpty() ||
                !text.contains("@") ||
                text.lastIndexOf(".") < text.lastIndexOf("@") ||
                text.lastIndexOf(".") == text.length() - 1) {
            setError("ایمیل وارد شده صحیح نمیباشد!!!");
            isValid = false;
        } else {
            isValid = true;
        }
    }


    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);

        String text = getText().toString();

        if (!text.isEmpty()) {
            if (!isMyTextValid(text)) {
                super.onFocusChanged(true, direction, previouslyFocusedRect);
            } else {
                super.onFocusChanged(false, direction, previouslyFocusedRect);

            }
        } else {
            if (mIsRequired == REQUIRED)
                setError("فیلد خالی !!!");
        }
    }
}
