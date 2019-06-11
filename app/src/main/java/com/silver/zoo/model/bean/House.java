package com.silver.zoo.model.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class House implements Serializable {

    @SerializedName("E_Name")
    private String name;

    @SerializedName("E_Info")
    private String info;

    @SerializedName("E_Memo")
    private String memo;

    @SerializedName("E_Category")
    private String category;

    @SerializedName("E_Pic_URL")
    private String picUrl;

    @SerializedName("E_URL")
    private String url;

    public House(String name, String info, String memo, String category, String picUrl, String url) {
        this.name = name;
        this.info = info;
        this.memo = memo;
        this.category = category;
        this.picUrl = picUrl;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public String getMemo() {
        return memo;
    }

    public String getCategory() {
        return category;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getUrl() {
        return url;
    }
}
