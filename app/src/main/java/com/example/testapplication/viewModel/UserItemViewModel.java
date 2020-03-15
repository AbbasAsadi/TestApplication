package com.example.testapplication.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.testapplication.model.User;

public class UserItemViewModel extends ViewModel {
    private User mUser;

    public UserItemViewModel(User user) {
        mUser = user;
    }

    public User getUser() {
        return mUser;
    }
}
