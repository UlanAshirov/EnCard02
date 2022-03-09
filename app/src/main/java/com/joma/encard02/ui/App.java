package com.joma.encard02.ui;

import android.app.Application;

import com.joma.encard02.data.network.PixabayApi;
import com.joma.encard02.data.network.RetrofitClient;
import com.joma.encard02.repositories.MainRepository;

public class App extends Application {
    public static RetrofitClient retrofitClient;
    public static PixabayApi api;
    public static MainRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitClient = new RetrofitClient();
        api = retrofitClient.getApi();
        repository = new MainRepository(api);
    }
}
