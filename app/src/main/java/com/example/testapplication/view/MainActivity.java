package com.example.testapplication.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.testapplication.R;
import com.example.testapplication.Repository;

public class MainActivity extends AppCompatActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonDialog = findViewById(R.id.dialog_button);
        Button buttonForm = findViewById(R.id.form_button);


        Repository.getInstance().setResponseBodyList();

        buttonDialog.setOnClickListener(v ->
                MainFragment.newInstance().show(getSupportFragmentManager(), null));

        buttonForm.setOnClickListener(v ->
                startActivity(FormActivity.newIntent(this)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.search);
        item.setOnMenuItemClickListener(item1 -> {
            startActivity(SearchActivity.newIntent(this));
            return super.onCreateOptionsMenu(menu);
        });
        return super.onCreateOptionsMenu(menu);
    }
}
