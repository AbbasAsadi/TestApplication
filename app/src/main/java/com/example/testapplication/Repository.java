package com.example.testapplication;

import android.util.Log;

import com.example.testapplication.model.ResponseBody;
import com.example.testapplication.network.RetrofitInstance;
import com.example.testapplication.network.interfaces.API;
import com.example.testapplication.viewModel.ItemViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private static final String TAG = "Repository";
    private static final String BASE_URL = "http://192.168.7.10/api/";
    public static final String TOKEN =
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAC2OUQrCMBBE77LfjSQha5P-eQcvsEk3EEUj3RZE8e5u0d8384Z5w9ZmmJyNbgDZMkxwKoVFzv3KdxigiSgr_XbIvPT-WliI9oCfD_Uw-nSMox-1Sesf2B-4rE1VQq7Iszeo3ASkZJJjMsHVFDj4bHPVOdlvpGjd5wtYT8idkwAAAA.zDPNcJbhMuGDzqXig5D4DHhpquqpOmetYMJrA_FNNUsInTlGvtb9ke2DfHsli199UuAw8GpMEMPgX1PhYUfNgw";
    private static Repository mInstance;
    private List<ResponseBody> mResponseBodyList = new ArrayList<>();
    private API mAPI = RetrofitInstance.getInstance(BASE_URL)
            .getRetrofit()
            .create(API.class);

    private Repository() {
    }

    public static Repository getInstance() {
        if (mInstance == null)
            mInstance = new Repository();

        return mInstance;
    }

    public List<ItemViewModel> getResponseBodyList() {
        List<ItemViewModel> itemViewModels = new ArrayList<>();
        for (ResponseBody responseBody : mResponseBodyList) {
            itemViewModels.add(new ItemViewModel(responseBody));
        }
        return itemViewModels;
    }

    public void setResponseBodyList() {
        Call<List<ResponseBody>> call = mAPI
                .getProductAnswer();

        call.enqueue(new Callback<List<ResponseBody>>() {
            @Override
            public void onResponse(Call<List<ResponseBody>> call, Response<List<ResponseBody>> response) {
                mResponseBodyList = response.body();
                if (mResponseBodyList.isEmpty() || mResponseBodyList == null) {
                    Log.d(TAG, "onResponse: " + "response is null");
                } else
                    Log.d(TAG, "onResponse: " + response.body().size());
            }

            @Override
            public void onFailure(Call<List<ResponseBody>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
