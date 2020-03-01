package com.example.testapplication.viewModel;


import androidx.lifecycle.ViewModel;

import com.example.testapplication.model.ResponseBody;

public class ItemViewModel extends ViewModel {
    private ResponseBody mResponseBody;

    public ItemViewModel(ResponseBody responseBody) {
        mResponseBody = responseBody;
    }

    public String getTitle() {
        return mResponseBody.getBody();
    }


    /*public ResponseBody getResponseBody() {
        return mResponseBody;
    }

    public void setResponseBody(ResponseBody responseBody) {
        mResponseBody = responseBody;
        notifyChange();
    }

    @Bindable
    public String getTitle() {
        return mResponseBody.getProductQuestion().getBody();
    }

    public void onItemClicked() {
    }*/
}
