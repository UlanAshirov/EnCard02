package com.joma.encard02.data.network;

import com.joma.encard02.data.model.PixabayResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PixabayApi {
    @GET("/api?key=26046390-cb448d9504b44ee5e839bc357")
    Call<PixabayResponse> getImages(@Query("q") String keyWord);
}
