package com.silver.zoo.model.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiHouse {

    @GET("apiAccess?scope=resourceAquire&rid=5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a")
    Observable<ResponseBody> read(
            @Query("q") String query,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset
    );
}
