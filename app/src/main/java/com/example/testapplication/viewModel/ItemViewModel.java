package com.example.testapplication.viewModel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.testapplication.model.ResponseBody;

public class ItemViewModel extends BaseObservable {
    private ResponseBody mResponseBody;


    public ResponseBody getResponseBody() {
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
    }
}
