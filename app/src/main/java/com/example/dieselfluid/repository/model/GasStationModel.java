package com.example.dieselfluid.repository.model;

import java.util.Map;

public class GasStationModel {
    private int page, perPage, totalCount, currentCount;
    private Map<String, String> gasStationData;

    public GasStationModel(int page, int perPage, int totalCount, int currentCount, Map<String, String> gasStationData) {
        this.page = page;
        this.perPage = perPage;
        this.totalCount = totalCount;
        this.currentCount = currentCount;
        this.gasStationData = gasStationData;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public Map<String, String> getGasStationData() {
        return gasStationData;
    }

    public void setGasStationData(Map<String, String> gasStationData) {
        this.gasStationData = gasStationData;
    }
}
