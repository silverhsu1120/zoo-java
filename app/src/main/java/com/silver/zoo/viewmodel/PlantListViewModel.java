package com.silver.zoo.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.silver.zoo.model.api.ApiPlantRes;
import com.silver.zoo.model.api.ApiServiceManager;
import com.silver.zoo.model.bean.Plant;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class PlantListViewModel extends ViewModel {

    private final MutableLiveData<List<Plant>> plants = new MutableLiveData<>();

    private CompositeDisposable disposable = new CompositeDisposable();

    public MutableLiveData<List<Plant>> getPlants() {
        return plants;
    }

    public void fetchPlantList(String query, Integer limit, Integer offset) {
        disposable.add(ApiServiceManager.getInstance().getApiPlant()
                .read(query, limit, offset)
                .subscribeOn(Schedulers.io())
                .map(new Function<ResponseBody, ApiPlantRes>() {
                    @Override
                    public ApiPlantRes apply(ResponseBody body) throws Exception {
                        String json = body.string();
                        JsonObject object = new Gson().fromJson(json, JsonObject.class);
                        String result = object.getAsJsonObject("result").toString();
                        return new Gson().fromJson(result, ApiPlantRes.class);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiPlantRes>() {
                    @Override
                    public void accept(ApiPlantRes res) throws Exception {
                        plants.setValue(res.getPlants());
                    }
                })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
