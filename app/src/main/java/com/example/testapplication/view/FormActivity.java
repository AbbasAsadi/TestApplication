package com.example.testapplication.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.example.testapplication.R;
import com.example.testapplication.customView.InputCustomView;

public class FormActivity extends AppCompatActivity {
    private InputCustomView mCustomViewName;
    private InputCustomView mCustomViewFamily;
    private InputCustomView mCustomViewEmail;
    private InputCustomView mCustomViewPhoneNumber;
    private Button mConfirmButton;
    private NestedScrollView mScrollView;

    public static Intent newIntent(Context context) {
        return new Intent(context, FormActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        initUI();


        mConfirmButton.setOnClickListener(v -> {
            mConfirmButton.setFocusable(true);
            mConfirmButton.requestFocus();
            if (!mCustomViewEmail.isMyTextValid()) {
                mScrollView.smoothScrollTo(0 , mCustomViewEmail.getTop() - 24 );
            } else if (!mCustomViewPhoneNumber.isMyTextValid()) {
                mScrollView.post(() -> mScrollView.smoothScrollTo(0 , mCustomViewPhoneNumber.getTop() - 24));
            } else if (!mCustomViewName.isMyTextValid()) {
                mScrollView.post(() -> mScrollView.smoothScrollTo(0 , mCustomViewName.getTop() - 24));
            } else if (!mCustomViewFamily.isMyTextValid()) {
                mScrollView.post(() -> mScrollView.smoothScrollTo(0 , mCustomViewFamily.getTop() - 24));
            } else {
                Toast.makeText(this, "ثبت نام با موفقیت انجام شد.", Toast.LENGTH_SHORT).show();
                startActivity(MainActivity.newIntent(this));
            }
        });
    }

    private void initUI() {
        mScrollView = findViewById(R.id.scroll_view);
        mConfirmButton = findViewById(R.id.confirm_button);
        mCustomViewName = findViewById(R.id.name_input);
        mCustomViewEmail = findViewById(R.id.email_input);
        mCustomViewPhoneNumber = findViewById(R.id.phone_number_input);
        mCustomViewFamily = findViewById(R.id.family_input);
    }
}
