package com.example.dieselfluid.viewmodel.repository.api.dto;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data
{
    @SerializedName("regDt")
    @Expose
    private String regDt;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("lng")
    @Expose
    private String lng;

    @SerializedName("color")
    @Expose
    private String color;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("tel")
    @Expose
    private String tel;

    @SerializedName("addr")
    @Expose
    private String addr;

    @SerializedName("inventory")
    @Expose
    private String inventory;

    @SerializedName("openTime")
    @Expose
    private String openTime;

    @SerializedName("lat")
    @Expose
    private String lat;

    public String getRegDt ()
    {
        return regDt;
    }

    public void setRegDt (String regDt)
    {
        this.regDt = regDt;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public String getLng ()
    {
        return lng;
    }

    public void setLng (String lng)
    {
        this.lng = lng;
    }

    public String getColor ()
    {
        return color;
    }

    public void setColor (String color)
    {
        this.color = color;
    }

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getTel ()
    {
        return tel;
    }

    public void setTel (String tel)
    {
        this.tel = tel;
    }

    public String getAddr ()
    {
        return addr;
    }

    public void setAddr (String addr)
    {
        this.addr = addr;
    }

    public String getInventory ()
    {
        return inventory;
    }

    public void setInventory (String inventory)
    {
        this.inventory = inventory;
    }

    public String getOpenTime ()
    {
        return openTime;
    }

    public void setOpenTime (String openTime)
    {
        this.openTime = openTime;
    }

    public String getLat ()
    {
        return lat;
    }

    public void setLat (String lat)
    {
        this.lat = lat;
    }

    @NonNull
    @Override
    public String toString()
    {
        return "ClassPojo [regDt = "+regDt+", code = "+code+", lng = "+lng+", color = "+color+", price = "+price+", name = "+name+", tel = "+tel+", addr = "+addr+", inventory = "+inventory+", openTime = "+openTime+", lat = "+lat+"]";
    }
}
