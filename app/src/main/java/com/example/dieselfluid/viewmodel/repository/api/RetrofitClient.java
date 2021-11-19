package com.example.dieselfluid.viewmodel.repository.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// 싱글톤 패턴
// 사용법
// 먼저 getInstance로 retrofit빌드 해주고,
// getRetrofitAPI를 사용하면 된다.

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private static RetrofitAPI retrofitAPI;
    //만약 레트로핏이
    private final static String BASE_URL = "https://api.odcloud.kr/api/";


    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public static RetrofitAPI getRetrofitAPI() {
        return retrofitAPI;
    }
}
