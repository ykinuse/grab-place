package com.grabtaxi.grabplace.services;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by ykinuse on 13/09/2015.
 */
public interface IGrabPlaceAPI
{
    @GET("/api/v1/places/search")
    void search(@Path("name") String name, Callback<Object> callback);
}
