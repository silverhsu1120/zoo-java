package com.silver.zoo.model;

import java.io.Serializable;

public class Plant implements Serializable {

    private String name;
    private String alias;
    private String intro;
    private String type;
    private String use;
    private int resId;

    public Plant(String name, String alias, String intro, String type, String use, int resId) {
        this.name = name;
        this.alias = alias;
        this.intro = intro;
        this.type = type;
        this.use = use;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public String getIntro() {
        return intro;
    }

    public String getType() {
        return type;
    }

    public String getUse() {
        return use;
    }

    public int getResId() {
        return resId;
    }
}
