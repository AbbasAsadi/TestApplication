package com.example.testapplication.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.testapplication.R;
import com.example.testapplication.Repository;

public class MainActivity extends AppCompatActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context , MainActivity.class);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonDialog = findViewById(R.id.dialog_button);
        Button buttonForm = findViewById(R.id.form_button);


        Repository.getInstance().setResponseBodyList();

        buttonDialog.setOnClickListener(v ->
                MainFragment.newInstance().show(getSupportFragmentManager() , null));

        buttonForm.setOnClickListener(v ->
                startActivity(FormActivity.newIntent(this)));



    }



}
