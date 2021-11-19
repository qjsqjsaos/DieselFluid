package com.example.dieselfluid.viewmodel.repository.api;

import com.example.dieselfluid.viewmodel.repository.api.dto.DieselDataClass;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RetrofitAPI {

    @GET("15094782/v1/uddi:6b2017af-659d-437e-a549-c59788817675")
    Call<DieselDataClass> getDieselData(@Query("serviceKey") String serviceKey, @Query("perPage") int perPage);
}
