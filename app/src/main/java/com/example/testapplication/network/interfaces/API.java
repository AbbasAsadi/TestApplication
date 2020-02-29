package com.example.testapplication.network.interfaces;

import com.example.testapplication.model.ResponseBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("idn/product_answers/my")
    Call<List<ResponseBody>> getProductAnswer();
}
