package com.joma.encard02.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.joma.encard02.common.Resource;
import com.joma.encard02.data.model.imageModel.Hit;
import com.joma.encard02.data.model.imageModel.PixabayResponse;
import com.joma.encard02.data.network.pixabay.PixabayApi;
import com.joma.encard02.data.model.videoModel.MainVideoResponce;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
    private final PixabayApi api;
    private final int limit = 10;
    private MutableLiveData<Resource<PixabayResponse<Hit>>> liveData = new MutableLiveData<>();

    @Inject
    public MainRepository(PixabayApi api) {
        this.api = api;
    }

    public MutableLiveData<Resource<PixabayResponse<Hit>>> getImages(String keyWord,
                                                                     int page) {
        liveData.setValue(Resource.loading());
        api.getImages(keyWord, page, limit).enqueue(new Callback<PixabayResponse<Hit>>() {
            @Override
            public void onResponse(@NotNull Call<PixabayResponse<Hit>> call,
                                   @NotNull Response<PixabayResponse<Hit>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                } else {
                    liveData.setValue(Resource.error(response.message()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<PixabayResponse<Hit>> call, @NotNull Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage()));
            }
        });
        return liveData;
    }

    public MutableLiveData<Resource<MainVideoResponce>> getVideo(String tags, int page) {
        MutableLiveData<Resource<MainVideoResponce>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getVideo(tags, page, limit)
                .enqueue(new Callback<MainVideoResponce>() {
                    @Override
                    public void onResponse(@NotNull Call<MainVideoResponce> call,
                                           @NotNull Response<MainVideoResponce> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Log.e("------", response.body() + "");
                            liveData.setValue(Resource.success(response.body()));
                        } else {
                            Log.e("------", response.message());
                            liveData.setValue(Resource.error(response.message()));
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<MainVideoResponce> call, @NotNull Throwable t) {
                        Log.e("------", t.getLocalizedMessage());
                        liveData.setValue(Resource.error(t.getLocalizedMessage()));
                    }
                });
        return liveData;
    }
}
