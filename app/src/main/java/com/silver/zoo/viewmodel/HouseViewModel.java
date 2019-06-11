package com.silver.zoo.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.silver.zoo.model.bean.House;

public class HouseViewModel extends ViewModel {

    private MutableLiveData<House> house = new MutableLiveData<>();

    public MutableLiveData<House> getHouse() {
        return house;
    }
}
