package com.example.testapplication.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;

import com.example.testapplication.R;
import com.example.testapplication.customView.Checker;

public class FormActivity extends AppCompatActivity {
    private Button mConfirmButton;
    private NestedScrollView mScrollView;
    private ConstraintLayout mConstraintLayout;

    public static Intent newIntent(Context context) {
        return new Intent(context, FormActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        initUI();


        mConfirmButton.setOnClickListener(v -> {
            Checker checker = new Checker(mScrollView, mConstraintLayout);
            if (checker.IsValid()) {
                Toast.makeText(this, "ثبت نام با موفقیت انجام شد.", Toast.LENGTH_SHORT).show();
                startActivity(MainActivity.newIntent(this));
            }
        });
    }

    private void initUI() {
        mScrollView = findViewById(R.id.scroll_view);
        mConstraintLayout = findViewById(R.id.constrain);
        mConfirmButton = findViewById(R.id.confirm_button);
    }
}
