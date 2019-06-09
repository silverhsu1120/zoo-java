package com.silver.zoo.model;

import java.io.Serializable;
import java.util.List;

public class House implements Serializable {

    private String name;
    private String intro;
    private String info;
    private int resId;
    private List<Plant> plants;

    public House(String name, String intro, String info, int resId, List<Plant> plants) {
        this.name = name;
        this.intro = intro;
        this.info = info;
        this.resId = resId;
        this.plants = plants;
    }

    public String getName() {
        return name;
    }

    public String getIntro() {
        return intro;
    }

    public String getInfo() {
        return info;
    }

    public int getResId() {
        return resId;
    }

    public List<Plant> getPlants() {
        return plants;
    }

}
