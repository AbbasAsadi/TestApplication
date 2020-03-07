package com.example.testapplication.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testapplication.R;
import com.example.testapplication.customView.InputCustomView;

public class FormActivity extends AppCompatActivity {
    private InputCustomView mCustomViewName;
    private InputCustomView mCustomViewFamily;
    private InputCustomView mCustomViewEmail;
    private InputCustomView mCustomViewPhoneNumber;
    private Button mConfirmButton;

    public static Intent newIntent(Context context) {
        return new Intent(context, FormActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        initUI();


        mConfirmButton.setOnClickListener(v -> {
            if (!mCustomViewEmail.isValid()) {
                mCustomViewEmail.setFocusable(true);
                mCustomViewEmail.requestFocus();
            } else if (!mCustomViewPhoneNumber.isValid()) {
                mCustomViewPhoneNumber.setFocusable(true);
                mCustomViewPhoneNumber.requestFocus();
            } else if (!mCustomViewName.isValid()) {
                mCustomViewName.setFocusable(true);
                mCustomViewName.requestFocus();
            } else if (!mCustomViewFamily.isValid()) {
                mCustomViewFamily.setFocusable(true);
                mCustomViewFamily.requestFocus();
            } else {
                Toast.makeText(this, "ثبت نام با موفقیت انجام شد.", Toast.LENGTH_SHORT).show();
                startActivity(MainActivity.newIntent(this));
            }
        });
    }

    private void initUI() {
        mConfirmButton = findViewById(R.id.confirm_button);
        mCustomViewName = findViewById(R.id.name_input);
        mCustomViewEmail = findViewById(R.id.email_input);
        mCustomViewPhoneNumber = findViewById(R.id.phone_number_input);
        mCustomViewFamily = findViewById(R.id.family_input);
    }
}
