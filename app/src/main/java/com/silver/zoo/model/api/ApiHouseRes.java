package com.silver.zoo.model.api;

import com.google.gson.annotations.SerializedName;
import com.silver.zoo.model.bean.House;

import java.util.List;

public class ApiHouseRes {

    @SerializedName("limit")
    private int limit;

    @SerializedName("offset")
    private int offset;

    @SerializedName("count")
    private int count;

    @SerializedName("sort")
    private String sort;

    @SerializedName("results")
    private List<House> houses;

    public ApiHouseRes(int limit, int offset, int count, String sort, List<House> houses) {
        this.limit = limit;
        this.offset = offset;
        this.count = count;
        this.sort = sort;
        this.houses = houses;
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

    public List<House> getHouses() {
        return houses;
    }
}
