package com.example.testapplication.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.testapplication.OfflineRepository;
import com.example.testapplication.OnlineRepository;
import com.example.testapplication.R;
import com.example.testapplication.RecyclerAdapter;
import com.example.testapplication.UserRecyclerAdapter;
import com.example.testapplication.databinding.ActivityMainBinding;
import com.example.testapplication.viewModel.UserItemViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserRecyclerAdapter mAdapter;
    private OfflineRepository mOfflineRepository;
    private ActivityMainBinding mBinding;
    private static MutableLiveData<List<UserItemViewModel>> mLiveUserViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    private static MutableLiveData<List<UserItemViewModel>> getmLiveUserViewModel() {
        if (mLiveUserViewModel == null){
            mLiveUserViewModel = new MutableLiveData<>();
        }
        return mLiveUserViewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<UserItemViewModel> items = OfflineRepository.getInstance().getUserViewModelList();
        getmLiveUserViewModel().setValue(items);

        mBinding = DataBindingUtil
                .inflate(LayoutInflater.from(this),
                        R.layout.activity_main,
                        null,
                        false);

        setContentView(mBinding.getRoot());
        mBinding.userListMain.setLayoutManager(new LinearLayoutManager(this));

        Observer<List<UserItemViewModel>> observer = itemViewModels -> {
            if (itemViewModels == null || itemViewModels.isEmpty()) {
                Toast.makeText(this, "لیست خالی میباشد!!!", Toast.LENGTH_SHORT).show();
            } else {
                mAdapter = new UserRecyclerAdapter(this, itemViewModels);
                mBinding.userListMain.setAdapter(mAdapter);
            }
        };
        getmLiveUserViewModel().observe(this , observer);

        OnlineRepository.getInstance().setResponseBodyList();

        mBinding.dialogButton.setOnClickListener(v ->
                MainFragment.newInstance().show(getSupportFragmentManager(), null));

        mBinding.formButton.setOnClickListener(v ->
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
