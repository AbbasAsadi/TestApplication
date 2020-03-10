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

/**
 * this class is a CustomView that extends {@link TextInputLayout}
 *
 * @author Abbas Asadi GitHub:AbbasAsadi
 * @since API 29
 */

public class InputCustomView extends TextInputLayout {
    private final String TAG = "InputCustomView";

    //describe that can be empty or not
    private final int REQUIRED = 0;
    private final int OPTIONAL = 1;

    //describe type of Input
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

    /**
     * this is constructor of this class
     * in this method we receive value of all attributes that we want, with a typeArray
     * after that setType of {@link TextInputEditText}
     * and with setOnFocusChangeListener we understand that focus changed and
     * checked validation with isMyTextValid() method
     * hasFocus is a boolean value that if focus is on a component that's value is true
     * <p>
     * **note that for use this CustomView you should use Checker Class for
     * checkValidation and should add attrs.xml file to your values of res file**
     *
     * @param context of parent
     * @param attrs   our custom attributes
     */
    public InputCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.custom_view_input, this, true);
        mEditText = findViewById(R.id.custom_text_input);

        getAttributesValue(attrs);

        if (mHint != null) {
            setHint(mHint);
        }

        setTextInputType();

        mEditText.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                isMyTextValid();
                Log.d(TAG, "InputCustomView: " + mIsValid);
            }
        });
    }

    /**
     * setInputType of our {@link TextInputEditText} with mTextType field
     */
    public void setTextInputType() {
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
    }

    /**
     * this method get values of our customAttributes
     *
     * @param attrs our custom Attribute that we get that in constructor
     */
    public void getAttributesValue(AttributeSet attrs) {
        mTypeArray = mContext.getTheme().obtainStyledAttributes(attrs, R.styleable.InputCustomView, 0, 0);
        try {
            mHint = mTypeArray.getString(R.styleable.InputCustomView_customViewHint);
            mIsRequired = mTypeArray.getInt(R.styleable.InputCustomView_customViewIsRequired, OPTIONAL);
            mTextType = mTypeArray.getInt(R.styleable.InputCustomView_customViewType, 0);
        } finally {
            mTypeArray.recycle();
        }
    }

    /**
     * if type of {@link TextInputEditText} is PHONE_NUMBER we limit length of that to 11
     */
    private void setMaxLengthForPhoneNumber() {
        int maxLength = 11;
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        mEditText.setFilters(fArray);
    }

    /**
     * according to type of {@link InputCustomView} we check validation
     *
     * @return false if inputValue is inValid
     */
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
        return mIsValid;
    }

    /**
     * this method has used {@link Patterns} class to check phone number validation
     *
     * @param text of {@link TextInputEditText} that get from isMyTextValid() method
     */
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
            } else {
                setErrorEnabled(false);
            }
        } else if (mIsRequired == REQUIRED) {
            Log.d(TAG, "isPhoneValid: in second if");
            mIsValid = false;
            setError("فیلد خالی");
        } else if (mIsRequired == OPTIONAL) {
            Log.d(TAG, "isPhoneValid: in third if");
            mIsValid = true;
        }
    }

    /**
     * this method checked if {@param text} contains number or not
     *
     * @param text of {@link TextInputEditText} that get from isMyTextValid() method
     */
    private void isNameValid(String text) {
        if (!text.isEmpty()) {
            Log.d(TAG, "isNameValid: in first if");
            if (text.matches(".*\\d.*")) {
                setError("نام نباید شامل عدد باشد!!!");
                mIsValid = false;
            } else {
                mIsValid = true;
                setErrorEnabled(false);
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

    /**
     * this method has used {@link Patterns} class to check email validation
     *
     * @param text of {@link TextInputEditText} that get from isMyTextValid() method
     */
    private void isEmailValid(String text) {
        if (!text.isEmpty()) {
            Matcher matcher = Patterns.EMAIL_ADDRESS.matcher(text);
            mIsValid = matcher.matches();
            if (!mIsValid)
                setError("ایمیل وارد شده صحیح نمیباشد!!!");
            else
                setErrorEnabled(false);
        } else if (mIsRequired == REQUIRED) {
            Log.d(TAG, "isEmailValid: in second if");
            mIsValid = false;
            setError("فیلد خالی");
        } else if (mIsRequired == OPTIONAL) {
            Log.d(TAG, "isEmailValid: in third if");
            mIsValid = true;
        }
    }
}
