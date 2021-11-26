package com.example.dieselfluid.viewmodel.repository.api.dto;

//DTO 클래스(Data Transfer Object)

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DieselData
{
    @SerializedName("perPage")
    @Expose
    private String perPage;

    @SerializedName("data")
    @Expose
    private Data[] data;

    @SerializedName("currentCount")
    @Expose
    private String currentCount;

    @SerializedName("page")
    @Expose
    private String page;

    @SerializedName("totalCount")
    @Expose
    private String totalCount;

    public String getPerPage ()
    {
        return perPage;
    }

    public void setPerPage (String perPage)
    {
        this.perPage = perPage;
    }

    public Data[] getData ()
    {
        return data;
    }

    public void setData (Data[] data)
    {
        this.data = data;
    }

    public String getCurrentCount ()
    {
        return currentCount;
    }

    public void setCurrentCount (String currentCount)
    {
        this.currentCount = currentCount;
    }

    public String getPage ()
    {
        return page;
    }

    public void setPage (String page)
    {
        this.page = page;
    }

    public String getTotalCount ()
    {
        return totalCount;
    }

    public void setTotalCount (String totalCount)
    {
        this.totalCount = totalCount;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [perPage = "+perPage+", data = "+data+", currentCount = "+currentCount+", page = "+page+", totalCount = "+totalCount+"]";
    }
}
