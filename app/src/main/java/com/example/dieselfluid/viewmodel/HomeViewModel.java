package com.example.dieselfluid.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dieselfluid.model.UserModel;


public class HomeViewModel extends ViewModel {
    private MutableLiveData<UserModel> user;

    public LiveData<UserModel> getUsers() {
        if (user == null) {
            user = new MutableLiveData<>();
            loadUsers();
        }
        return user;
    }

    private void loadUsers() {
        //여기에 유저 정보(경도, 위도 가져오기)

    }
}
