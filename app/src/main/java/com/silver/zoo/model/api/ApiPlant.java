package com.silver.zoo.model.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiPlant {

    @GET("apiAccess?scope=resourceAquire&rid=f18de02f-b6c9-47c0-8cda-50efad621c14")
    Observable<ResponseBody> read(
            @Query("q") String query,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset
    );
}
