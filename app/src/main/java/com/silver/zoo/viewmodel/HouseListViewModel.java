package com.silver.zoo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.silver.zoo.model.House;

import java.util.List;

public class HouseListViewModel extends AndroidViewModel {

    private MutableLiveData<List<House>> houses = new MutableLiveData<>();

    public HouseListViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<House>> getHouses() {
        return houses;
    }
}
