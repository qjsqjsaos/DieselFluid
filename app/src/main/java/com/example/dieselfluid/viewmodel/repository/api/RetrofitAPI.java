package com.example.dieselfluid.viewmodel.repository.api;

import com.example.dieselfluid.viewmodel.repository.api.dto.DieselData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RetrofitAPI {

    @GET("uws/v1/inventory")
    Call<DieselData> getDieselData(@Query("serviceKey") String serviceKey, @Query("perPage") int perPage);
}
