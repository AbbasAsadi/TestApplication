package com.example.testapplication.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.example.testapplication.R;
import com.google.android.material.textfield.TextInputEditText;

public class InputCustomView extends TextInputEditText {
    private Context mContext;
    //private TextInputEditText mEditText;

    private int mTextType;
    //private String mText;
    private String mHint;
    private TypedArray mTypeArray;
    // private boolean mIsRequired;

    public InputCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        mTypeArray = mContext.getTheme().obtainStyledAttributes(attrs, R.styleable.InputCustomView, 0, 0);
       /* LayoutInflater.from(context).inflate(R.layout.custom_view_input, this, true);
        mEditText = findViewById(R.id.custom_text_input);*/
        try {
           // mText = mTypeArray.getString(R.styleable.InputCustomView_customViewText);
            mHint = mTypeArray.getString(R.styleable.InputCustomView_customViewHint);
            // mIsRequired = mTypeArray.getBoolean(R.styleable.InputCustomView_customViewIsRequired, false);
            mTextType = mTypeArray.getInt(R.styleable.InputCustomView_customViewType, 0);

        } finally {
            mTypeArray.recycle();
        }
        if (/*isMyTextValid() &&*/ isMyHintValid()) {
            init(mHint);
        } else {
            setError("input data is wrong");
        }

    }

    private boolean isMyHintValid() {
        return mHint != null;
    }

    private boolean isMyTextValid(CharSequence text) {
        String mText = text.toString();
        if (mText != null) {
            if (mTextType == TextType.EMAIL.getValue()) {
                if (mText.isEmpty() || !mText.contains("@") || mText.lastIndexOf(".") < mText.lastIndexOf("@")) {
                    setError("ایمیل وارد شده صحیح نمیباشد!!!");
                    //throw new RuntimeException("no suitable email provided");
                    return false;
                } else
                    return true;
            }
            if (mTextType == TextType.NAME.getValue()) {
                if (mText.matches(".*\\d.*")) {
                    setError("نام نباید شامل عدد باشد!!!");
//                    throw new RuntimeException("no suitable name provided");
                    return false;
                } else
                    return true;
            }
            if (mTextType == TextType.PHONE_NUMBER.getValue()) {
                if (mText.length() != 11 || !mText.startsWith("09")) {
                    setError("شماره وارد شده صحیح نمیباشد!!!");
//                    throw new RuntimeException("no suitable phoneNumber provided");
                    return false;
                } else
                    return true;
            }
        } else {
            return false;
           /* if (mIsRequired) {
                mEditText.setError("فیلد خالی است!!!");
                // throw new RuntimeException("no text provided");
                return false;
            } else {
                return true;
            }*/
        }
        return false;
    }


    @Override
    public void setText(CharSequence text, BufferType type) {
        if (text!= null && !text.toString().isEmpty() && isMyTextValid(text)) {
            super.setText(text, type);
        }else {
            setError("ورودی نامعتبر است");
        }
    }

    private void init(String hint) {
        setHint(hint);
        //setText(text);
    }


}
