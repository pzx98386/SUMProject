package com.example.sumproject;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/Devices")
    Observable<Response<List<Devices>>> getDevices();
}
