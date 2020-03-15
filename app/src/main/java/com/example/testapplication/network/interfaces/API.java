package com.example.testapplication.network.interfaces;

import com.example.testapplication.productAnswerModel.ResponseBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("idn/product-answers/my")
    Call<List<ResponseBody>> getProductAnswer();
}
