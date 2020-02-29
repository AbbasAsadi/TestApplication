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
    private static RetrofitInstance sInstance;
    private Retrofit mRetrofit;

    private RetrofitInstance(String baseUrl) {

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain ->
        {
            Request newRequest  = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer" + Repository.TOKEN)
                    .build();
            return chain.proceed(newRequest);
        }).build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       /* HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())

                .build();
*/
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
/*
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("User-Agent" , CallApiHelper.createUserAgent());
        if(TokenManager.getToken()!=null){
            builder.addHeader(AUTHORIZATION, TokenManager.getToken());
        }
        return chain.proceed(builder.build());
    }*/
}
