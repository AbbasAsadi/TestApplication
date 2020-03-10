package com.example.testapplication.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;

import com.example.testapplication.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;

public class InputCustomView extends TextInputLayout {
    private final String TAG = "InputCustomView";
    private final int REQUIRED = 0;
    private final int OPTIONAL = 1;

    private final int NAME = 1;
    private final int PHONE_NUMBER = 2;
    private final int EMAIL = 3;

    private TextInputEditText mEditText;
    private Context mContext;
    private int mTextType;
    private String mHint;
    private TypedArray mTypeArray;
    private int mIsRequired;
    private boolean mIsValid;

    public InputCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.custom_view_input, this, true);
        mEditText = findViewById(R.id.custom_text_input);

        mTypeArray = mContext.getTheme().obtainStyledAttributes(attrs, R.styleable.InputCustomView, 0, 0);
        try {
            mHint = mTypeArray.getString(R.styleable.InputCustomView_customViewHint);
            mIsRequired = mTypeArray.getInt(R.styleable.InputCustomView_customViewIsRequired, OPTIONAL);
            mTextType = mTypeArray.getInt(R.styleable.InputCustomView_customViewType, 0);
        } finally {
            mTypeArray.recycle();
        }
        if (mHint != null) {
            setHint(mHint);
        }
        switch (mTextType) {
            case NAME:
                mEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                break;
            case PHONE_NUMBER:
                mEditText.setInputType(InputType.TYPE_CLASS_PHONE);
                setMaxLengthForPhoneNumber();
                break;
            case EMAIL:
                mEditText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;
        }
        mEditText.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                isMyTextValid();
                Log.d(TAG, "InputCustomView: " + mIsValid);
            }
        });
    }

    private void setMaxLengthForPhoneNumber() {
        int maxLength = 11;
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        mEditText.setFilters(fArray);
    }

    public boolean isMyTextValid() {
        String text = mEditText.getText().toString();
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
        if (mIsValid)
            setErrorEnabled(false);
        return mIsValid;
    }

    private void isPhoneNumberValid(String text) {
        if (!text.isEmpty()) {
            if (text.length() == 11 && text.startsWith("09")) {
                Matcher matcher = Patterns.PHONE.matcher(text);
                mIsValid = matcher.matches();
            } else {
                mIsValid = false;
            }

            if (!mIsValid) {
                setError("شماره وارد شده صحیح نمیباشد!!!");
            }
        } else if (mIsRequired == REQUIRED) {
            Log.d(TAG, "isNameValid: in second if");
            mIsValid = false;
            setError("فیلد خالی");
        } else if (mIsRequired == OPTIONAL) {
            Log.d(TAG, "isNameValid: in third if");
            mIsValid = true;
        }
    }

    private void isNameValid(String text) {
        if (!text.isEmpty()) {
            Log.d(TAG, "isNameValid: in first if");
            if (text.matches(".*\\d.*")) {
                setError("نام نباید شامل عدد باشد!!!");
                mIsValid = false;
            } else {
                mIsValid = true;
            }
        } else if (mIsRequired == REQUIRED) {
            Log.d(TAG, "isNameValid: in second if");
            mIsValid = false;
            setError("فیلد خالی");
        } else if (mIsRequired == OPTIONAL) {
            Log.d(TAG, "isNameValid: in third if");
            mIsValid = true;
        }
    }

    private void isEmailValid(String text) {
        if (!text.isEmpty()) {
            Matcher matcher = Patterns.EMAIL_ADDRESS.matcher(text);
            mIsValid = matcher.matches();
            if (!mIsValid)
                setError("ایمیل وارد شده صحیح نمیباشد!!!");
        } else if (mIsRequired == REQUIRED) {
            Log.d(TAG, "isNameValid: in second if");
            mIsValid = false;
            setError("فیلد خالی");
        } else if (mIsRequired == OPTIONAL) {
            Log.d(TAG, "isNameValid: in third if");
            mIsValid = true;
        }
    }
}
