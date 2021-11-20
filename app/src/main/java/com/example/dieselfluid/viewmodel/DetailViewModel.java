package com.example.dieselfluid.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dieselfluid.model.GasStationModel;


public class DetailViewModel extends ViewModel {
    private MutableLiveData<GasStationModel> gasModel = new MutableLiveData<>();

    public MutableLiveData<GasStationModel> getGasData() {
        Log.d("value333", String.valueOf(gasModel.getValue()));
        if (gasModel == null) {
            gasModel = new MutableLiveData<>();
        }
        return gasModel;
    }

    public void setGasData(GasStationModel model) {
        Log.d("value23", String.valueOf(model));
        gasModel.setValue(model);
    }
}
