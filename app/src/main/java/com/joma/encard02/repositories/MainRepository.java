package com.joma.encard02.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.joma.encard02.R;
import com.joma.encard02.common.Resource;
import com.joma.encard02.data.model.PixabayResponse;
import com.joma.encard02.data.network.PixabayApi;
import com.joma.encard02.data.videoModel.MainVideoResponce;
import com.joma.encard02.ui.App;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
    private PixabayApi api;

    public MainRepository(PixabayApi api) {
        this.api = api;
    }

    public MutableLiveData<Resource<PixabayResponse>> getImages(String keyWord) {
        MutableLiveData<Resource<PixabayResponse>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getImages(keyWord).enqueue(new Callback<PixabayResponse>() {
            @Override
            public void onResponse(Call<PixabayResponse> call, Response<PixabayResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                } else {
                    liveData.setValue(Resource.error(response.message()));
                }
            }

            @Override
            public void onFailure(Call<PixabayResponse> call, Throwable t) {
                Log.d("------", t.getLocalizedMessage());
                liveData.setValue(Resource.error(t.getLocalizedMessage()));
            }
        });
        return liveData;
    }

    public MutableLiveData<Resource<MainVideoResponce>> getVideo(String tags) {
        MutableLiveData<Resource<MainVideoResponce>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getVideo(tags)
                .enqueue(new Callback<MainVideoResponce>() {
            @Override
            public void onResponse(Call<MainVideoResponce> call, Response<MainVideoResponce> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("------", response.body() + "");
                    liveData.setValue(Resource.success(response.body()));
                } else {
                    Log.e("------", response.message());
                    liveData.setValue(Resource.error(response.message()));
                }
            }

            @Override
            public void onFailure(Call<MainVideoResponce> call, Throwable t) {
                Log.e("------", t.getLocalizedMessage());
                liveData.setValue(Resource.error(t.getLocalizedMessage()));
            }
        });
        return liveData;
    }
}
