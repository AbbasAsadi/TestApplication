package com.example.testapplication.viewModel;


import androidx.lifecycle.ViewModel;

import com.example.testapplication.productAnswerModel.ResponseBody;

public class ItemViewModel extends ViewModel {
    private ResponseBody mResponseBody;

    public ItemViewModel(ResponseBody responseBody) {
        mResponseBody = responseBody;
    }

    public String getTitle() {
        return mResponseBody.getBody();
    }

}
