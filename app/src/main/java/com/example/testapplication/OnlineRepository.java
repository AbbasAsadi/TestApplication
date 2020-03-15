package com.example.testapplication;

import android.util.Log;

import com.example.testapplication.productAnswerModel.ResponseBody;
import com.example.testapplication.network.RetrofitInstance;
import com.example.testapplication.network.interfaces.API;
import com.example.testapplication.viewModel.ItemViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnlineRepository {
    private static final String TAG = "Repository";
    private static final String BASE_URL = "http://192.168.7.10/api/";
    public static final String TOKEN =
            "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAACWOUQrCMBBE77LfjaRNbLL98w5eYJtuIIpGui2I4t3d0L_hDW-YL-xlgakfOpB9hgkuKbHItd75CR0UEWWpPk4zr7V-VhaiVvD7pdY5ut7G2OxC2wGsP8BtK6raET1yCAbzmI1fbDA0oKbkkgveRaKsc9JOoIq_P68Xr3aRAAAA.F4Z139aCjjolxvSf4ityJf6XDgh77iCH5SS9n3PdyBJKm0jxEhrrXFUAwahzDSws-iJdwFPX866Mca0Q-IHQig";
    private static OnlineRepository mInstance;
    private List<ResponseBody> mResponseBodyList = new ArrayList<>();
    private API mAPI = RetrofitInstance.getInstance(BASE_URL)
            .getRetrofit()
            .create(API.class);

    private OnlineRepository() {
    }

    public static OnlineRepository getInstance() {
        if (mInstance == null)
            mInstance = new OnlineRepository();

        return mInstance;
    }

    public List<ItemViewModel> getResponseBodyList() {
        List<ItemViewModel> itemViewModels = new ArrayList<>();
        if (mResponseBodyList != null && !mResponseBodyList.isEmpty()) {
            for (ResponseBody responseBody : mResponseBodyList) {
                itemViewModels.add(new ItemViewModel(responseBody));
            }
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
                if (mResponseBodyList == null || mResponseBodyList.isEmpty()) {
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
