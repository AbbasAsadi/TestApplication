package com.example.testapplication.viewModel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

public class SearchActivityViewModel extends ViewModel {
    private static final String TAG = "SearchActivityViewModel";
    private static SearchActivityViewModel mInstance;

    public static SearchActivityViewModel getInstance() {
        if (mInstance == null) {
            mInstance = new SearchActivityViewModel();
        }
        return mInstance;
    }

    public void doSearch(String query) {
        Log.d(TAG, "doSearch: " + query);
    }
}
