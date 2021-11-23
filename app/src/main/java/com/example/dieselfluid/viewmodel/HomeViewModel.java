package com.example.dieselfluid.viewmodel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dieselfluid.model.GasStationModel;
import com.example.dieselfluid.viewmodel.repository.api.RetrofitAPI;
import com.example.dieselfluid.viewmodel.repository.api.RetrofitClient;
import com.example.dieselfluid.viewmodel.repository.api.dto.DieselData;
import com.example.dieselfluid.viewmodel.repository.api.dto.DieselDataClass;
import com.google.android.gms.ads.interstitial.InterstitialAd;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeViewModel extends ViewModel {
    private MutableLiveData<ArrayList<GasStationModel>> dieseldata;
    private MutableLiveData<GasStationModel> gasModel;
    private RetrofitAPI retrofitAPI;
    private final static String SECRET_KEY = "+VSUnLfNB7xrQXw57u4L/6IDgIBuw4DIYKm6lax8dyEwZ5WkNzj2tQR5U9a1A3J6RSW/KD40199fXwI7RLhQGg==";


    public MutableLiveData<ArrayList<GasStationModel>> getDieselData() {
        if (dieseldata == null) {
            dieseldata = new MutableLiveData<>();
            loadDieselData();
        }
        return dieseldata;
    }

    private void loadDieselData() {
        RetrofitClient retrofitClient = RetrofitClient.getInstance();

        if (retrofitClient != null) {
            retrofitAPI = RetrofitClient.getRetrofitAPI();
            retrofitAPI.getDieselData(SECRET_KEY, 1000).enqueue(new Callback<DieselDataClass>() {
                @Override
                public void onResponse(Call<DieselDataClass> call, Response<DieselDataClass> response) {
                    DieselDataClass dieseldataClass = response.body();
                    DieselData[] data = dieseldataClass != null
                            ? dieseldataClass.getDieselData()
                            : new DieselData[0];

                    ArrayList<GasStationModel> insertData = new ArrayList<>();

                    for (int i = 0; i < data.length; i++) {
                        String address = data[i].getAddress();
                        String operatingTime = data[i].getOperatingTime();
                        String price = data[i].getPrice();
                        String getDetailAddress = data[i].getDetailAddress();
                        String dieselStock = data[i].getDieselStock();
                        String phoneNumber = data[i].getPhoneNumber();
                        String updateDate = data[i].getUpdateDate();

                        insertData.add(new GasStationModel(
                                getDetailAddress,
                                operatingTime,
                                price,
                                address,
                                dieselStock,
                                phoneNumber,
                                updateDate
                        ));
                    }

                    dieseldata.setValue(insertData);
                }

                @Override
                public void onFailure(Call<DieselDataClass> call, Throwable t) {
                    //만약 서버통신이 안될때
                }
            });
        }
    }

    public MutableLiveData<GasStationModel> getOneGasData() {
        if (gasModel == null) {
            gasModel = new MutableLiveData<>();
        }
        return gasModel;
    }

    public void setOneGasData(GasStationModel model) {
        Log.d("value24", String.valueOf(model.getDetailAddress()));
        gasModel.setValue(model);
    }
}
