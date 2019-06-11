package com.silver.zoo.model.api;

import com.google.gson.annotations.SerializedName;
import com.silver.zoo.model.bean.Plant;

import java.util.List;

public class ApiPlantRes {

    @SerializedName("limit")
    private int limit;

    @SerializedName("offset")
    private int offset;

    @SerializedName("count")
    private int count;

    @SerializedName("sort")
    private String sort;

    @SerializedName("results")
    private List<Plant> plants;

    public ApiPlantRes(int limit, int offset, int count, String sort, List<Plant> plants) {
        this.limit = limit;
        this.offset = offset;
        this.count = count;
        this.sort = sort;
        this.plants = plants;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

    public int getCount() {
        return count;
    }

    public String getSort() {
        return sort;
    }

    public List<Plant> getPlants() {
        return plants;
    }
}
