package com.example.dieselfluid.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dieselfluid.model.GasStationModel;
import com.example.dieselfluid.viewmodel.repository.api.RetrofitAPI;
import com.example.dieselfluid.viewmodel.repository.api.RetrofitClient;
import com.example.dieselfluid.viewmodel.repository.api.dto.DieselData;
import com.example.dieselfluid.viewmodel.repository.api.dto.DieselDataClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeViewModel extends ViewModel {
    private MutableLiveData<GasStationModel> dieseldata;
    private RetrofitAPI retrofitAPI;
    private final static String SECRET_KEY = "+VSUnLfNB7xrQXw57u4L/6IDgIBuw4DIYKm6lax8dyEwZ5WkNzj2tQR5U9a1A3J6RSW/KD40199fXwI7RLhQGg==";


    public LiveData<GasStationModel> getDieselData() {
        if (dieseldata == null) {
            dieseldata = new MutableLiveData<GasStationModel>();
            loadDieselData();
        }
        return dieseldata;
    }

    private void loadDieselData() {
        RetrofitClient retrofitClient = RetrofitClient.getInstance();

        if (retrofitClient != null) {
            Log.d("retrofit", "test");
            retrofitAPI = RetrofitClient.getRetrofitAPI();
            retrofitAPI.getDieselData(SECRET_KEY, 1000).enqueue(new Callback<DieselDataClass>() {
                @Override
                public void onResponse(Call<DieselDataClass> call, Response<DieselDataClass> response) {
                    DieselDataClass dieselData = response.body();
                    DieselData[] data = dieselData != null
                            ? dieselData.getDieselData()
                            : new DieselData[0];


                    for (int i = 0; i < data.length; i++) {
                        String address = data[i].getAddress();
                        String operatingTime = data[i].getOperatingTime();
                        String price = data[i].getPrice();
                        String gasStationName = data[i].getGasStationName();
                        String dieselStock = data[i].getDieselStock();
                        String phoneNumber = data[i].getPhoneNumber();
                        String updateDate = data[i].getUpdateDate();

                        dieseldata.setValue(new GasStationModel(
                                address,
                                operatingTime,
                                price,
                                gasStationName,
                                dieselStock,
                                phoneNumber,
                                updateDate
                        ));

                        ///이녀석 리스트로 바꾸어 주어야 함
                    }


                }

                @Override
                public void onFailure(Call<DieselDataClass> call, Throwable t) {
                    Log.d("retrofit2", t.getMessage());
                }
            });
        }


    }
}
