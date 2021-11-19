package com.example.dieselfluid.viewmodel.repository.api.dto;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DieselData {

    @SerializedName("코드")
    @Expose
    private String code;

    @SerializedName("주소")
    @Expose
    private String address;

    @SerializedName("영업시간")
    @Expose
    private String operating_time;

    @SerializedName("가격")
    @Expose
    private String price;

    @SerializedName("명칭")
    @Expose
    private String gas_station_name;

    @SerializedName("위도")
    @Expose
    private String latitude;

    @SerializedName("재고량")
    @Expose
    private String diesel_stock;

    @SerializedName("전화번호")
    @Expose
    private String phone_number;

    @SerializedName("데이터기준일")
    @Expose
    private String update_date;

    @SerializedName("경도")
    @Expose
    private String longitude;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOperatingTime() {
        return operating_time;
    }

    public void setOperatingTime(String operatingTime) {
        this.operating_time = operatingTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGasStationName() {
        return gas_station_name;
    }

    public void setGasStationName(String gasStationName) {
        this.gas_station_name = gasStationName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDieselStock() {
        return diesel_stock;
    }

    public void setDieselStock(String dieselStock) {
        this.diesel_stock = dieselStock;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone_number = phoneNumber;
    }

    public String getUpdateDate() {
        return update_date;
    }

    public void setUpdateDate(String updateDate) {
        this.update_date = updateDate;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @NonNull
    @Override
    public String toString() {
        return "ClassPojo [code = " + code + ", address = " + address + ", operating_time = " + operating_time + ", price = " + price + ", gas_station_name = " + gas_station_name + ", latitude = " + latitude + ", diesel_stock = " + diesel_stock + ", phone_number = " + phone_number + ", update_date = " + update_date + ", longitude = " + longitude + "]";
    }
}