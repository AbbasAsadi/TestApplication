package com.example.testapplication.network;

import com.example.testapplication.Repository;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public static final String AUTHORIZATION = "Authorization";
    private static RetrofitInstance sInstance;
    private Retrofit mRetrofit;

    private RetrofitInstance(String baseUrl) {

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain ->
        {
            Request newRequest  = chain.request().newBuilder()
                    .addHeader(AUTHORIZATION, Repository.TOKEN)
                    .build();
            return chain.proceed(newRequest);
        }).build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitInstance getInstance(String baseUrl) {
        if (sInstance == null) {
            sInstance = new RetrofitInstance(baseUrl);
        }
        return sInstance;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }
}
