package com.example.sumproject;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void initRecyclerView(List<Devices> devices) {
        RecyclerView recyclerView = findViewById(R.id.rvDevicesList);
        if (recyclerView == null) {
            return;
        }
        if (devices == null) {
            return;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DeviceAdapter devicesAdapter = new DeviceAdapter(this, devices);
        recyclerView.setAdapter(devicesAdapter);
    }

    private void getDevicesApiCall() {
        ApiUtils.getApiService().getDevices()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Response<List<Devices>>>() {
                    @Override
                    public void onNext(Response<List<Devices>> response) {
                        if (ApiUtils.getResponseStatusCode(response) == 200) {
                            initRecyclerView(response.body());
                        } //if
                    }

                    @Override
                    public void onError(Throwable ex) {
                        Log.e("API_CALL", ex.getMessage(), ex);
                    }

                    @Override
                    public void onComplete() {
                        //nothing here
                    }
                });
    }
}