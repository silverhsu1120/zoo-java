package com.silver.zoo.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.silver.zoo.model.Plant;

public class PlantViewModel extends ViewModel {

    private MutableLiveData<Plant> plant = new MutableLiveData<>();

    public MutableLiveData<Plant> getPlant() {
        return plant;
    }
}
