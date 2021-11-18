package com.example.dieselfluid.model;

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
}
