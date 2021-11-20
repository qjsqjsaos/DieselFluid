package com.example.dieselfluid.model;

public class GasStationModel {
    private String location;
    private String operating_time;
    private String price;
    private String detail_address;
    private String diesel_stock;
    private String phone_number;
    private String update_date;

    public GasStationModel(
            String location,
            String operating_time,
            String price,
            String detail_address,
            String diesel_stock,
            String phone_number,
            String update_date
    ) {
        this.location = location;
        this.operating_time = operating_time;
        this.price = price;
        this.detail_address = detail_address;
        this.diesel_stock = diesel_stock;
        this.phone_number = phone_number;
        this.update_date = update_date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getDetailAddress() {
        return detail_address;
    }

    public void setDetailAddress(String detail_address) {
        this.detail_address = detail_address;
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
