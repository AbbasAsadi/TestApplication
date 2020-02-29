package com.example.testapplication.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.testapplication.R;
import com.example.testapplication.RecyclerAdapter;
import com.example.testapplication.Repository;
import com.example.testapplication.databinding.FragmentMainBinding;
import com.example.testapplication.model.ResponseBody;

import java.util.List;
import java.util.Observable;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends DialogFragment {
    private static final String TAG = "MainFragment";
    private FragmentMainBinding mBinding;
    private RecyclerAdapter mAdapter;
    private static MutableLiveData<List<ResponseBody>> mLiveItemList;

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private MutableLiveData<List<ResponseBody>> getLiveItemList() {
        if (mLiveItemList == null) {
            mLiveItemList = new MutableLiveData<>();
        }
        return mLiveItemList;
    }


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<ResponseBody> items = Repository.getInstance().getResponseBodyList();
        getLiveItemList().setValue(items);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: ");

        mBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_main, container, false);

        mBinding.recyclerViewFragment.setLayoutManager(new LinearLayoutManager(getActivity()));

        Observer<List<ResponseBody>> observer = responseBodies -> {
            if (responseBodies == null || responseBodies.isEmpty()) {
                Toast.makeText(getActivity(), "لیست خالی میباشد!!!", Toast.LENGTH_SHORT).show();
            } else {
                mAdapter = new RecyclerAdapter(getActivity(), responseBodies);
                mBinding.recyclerViewFragment.setAdapter(mAdapter);
            }
        };
        getLiveItemList().observe(this, observer);


        return mBinding.getRoot();
    }

    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        super.show(manager, tag);

    }
}
