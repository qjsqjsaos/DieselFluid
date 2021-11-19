package com.example.dieselfluid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class GasStationModel {
    private String address;
    private String operating_time;
    private String price;
    private String gas_station_name;
    private String diesel_stock;
    private String phone_number;
    private String update_date;

    public GasStationModel(
            String address,
            String operating_time,
            String price,
            String gas_station_name,
            String diesel_stock,
            String phone_number,
            String update_date
    ) {
        this.address = address;
        this.operating_time = operating_time;
        this.price = price;
        this.gas_station_name = gas_station_name;
        this.diesel_stock = diesel_stock;
        this.phone_number = phone_number;
        this.update_date = update_date;
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

    public void setOperatingTime(String operating_time) {
        this.operating_time = operating_time;
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

    public void setGasStationName(String gas_station_name) {
        this.gas_station_name = gas_station_name;
    }

    public String getDieselStock() {
        return diesel_stock;
    }

    public void setDieselStock(String diesel_stock) {
        this.diesel_stock = diesel_stock;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getUpdateDate() {
        return update_date;
    }

    public void setUpdateDate(String update_date) {
        this.update_date = update_date;
    }
}
