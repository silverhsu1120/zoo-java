package com.silver.zoo.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.silver.zoo.model.api.ApiHouseRes;
import com.silver.zoo.model.api.ApiServiceManager;
import com.silver.zoo.model.bean.House;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class HouseListViewModel extends ViewModel {

    private final MutableLiveData<List<House>> houses = new MutableLiveData<>();

    private CompositeDisposable disposable = new CompositeDisposable();

    public MutableLiveData<List<House>> getHouses() {
        return houses;
    }

    public void fetchHouseList(String query, Integer limit, Integer offset) {
        disposable.add(ApiServiceManager.getInstance().getApiHouse()
                .read(query, limit, offset)
                .subscribeOn(Schedulers.io())
                .map(new Function<ResponseBody, ApiHouseRes>() {
                    @Override
                    public ApiHouseRes apply(ResponseBody body) throws Exception {
                        String json = body.string();
                        JsonObject object = new Gson().fromJson(json, JsonObject.class);
                        String result = object.getAsJsonObject("result").toString();
                        return new Gson().fromJson(result, ApiHouseRes.class);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiHouseRes>() {
                    @Override
                    public void accept(ApiHouseRes res) throws Exception {
                        houses.setValue(res.getHouses());
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
