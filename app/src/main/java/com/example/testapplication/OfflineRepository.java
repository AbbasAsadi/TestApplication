package com.example.testapplication;

import com.example.testapplication.model.User;
import com.example.testapplication.viewModel.UserItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class OfflineRepository {
    private static OfflineRepository mInstance;
    private List<User> mUserList = new ArrayList<>();

    public static OfflineRepository getInstance() {
        if (mInstance == null)
            mInstance = new OfflineRepository();

        return mInstance;
    }

    public List<UserItemViewModel> getUserViewModelList() {
        List<UserItemViewModel> itemViewModelList = new ArrayList<>();
        for (User user:mUserList) {
            itemViewModelList.add(new UserItemViewModel(user));
        }

        return itemViewModelList;
    }

    public void addUser(User user) {
        mUserList.add(user);
    }
}
