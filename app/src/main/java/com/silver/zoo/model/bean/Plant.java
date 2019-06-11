package com.silver.zoo.model.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Plant implements Serializable {


    @SerializedName("F_Name_Ch")
    private String chineseName;

    @SerializedName("F_Name_En")
    private String englishName;

    @SerializedName("F_AlsoKnown")
    private String alias;

    @SerializedName("F_Brief")
    private String brief;

    @SerializedName("F_Feature")
    private String feature;

    @SerializedName("F_Function&Application")
    private String function;

    @SerializedName("F_Pic01_URL")
    private String picUrl;

    @SerializedName("F_Update")
    private String update;

    public Plant(String chineseName, String englishName, String alias, String brief, String feature, String function, String update) {
        this.chineseName = chineseName;
        this.englishName = englishName;
        this.alias = alias;
        this.brief = brief;
        this.feature = feature;
        this.function = function;
        this.update = update;
    }

    public String getChineseName() {
        return chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getAlias() {
        return alias;
    }

    public String getBrief() {
        return brief;
    }

    public String getFeature() {
        return feature;
    }

    public String getFunction() {
        return function;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getUpdate() {
        return update;
    }
}
