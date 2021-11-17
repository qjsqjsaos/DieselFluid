package com.example.dieselfluid.repository.model;

public class UserModel {
    private String myLatitude, myLongitude; //위도 //경도

    public UserModel(String myLatitude, String myLongitude) {
        this.myLatitude = myLatitude;
        this.myLongitude = myLongitude;
    }

    public String getMyLatitude() {
        return myLatitude;
    }

    public void setMyLatitude(String myLatitude) {
        this.myLatitude = myLatitude;
    }

    public String getMyLongitude() {
        return myLongitude;
    }

    public void setMyLongitude(String myLongitude) {
        this.myLongitude = myLongitude;
    }
}
