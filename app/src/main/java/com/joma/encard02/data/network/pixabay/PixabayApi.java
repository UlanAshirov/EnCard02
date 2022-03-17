package com.joma.encard02.data.network.pixabay;

import com.joma.encard02.data.model.imageModel.Hit;
import com.joma.encard02.data.model.imageModel.PixabayResponse;
import com.joma.encard02.data.model.videoModel.MainVideoResponce;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PixabayApi {
    @GET("/api?key=26046390-cb448d9504b44ee5e839bc357")
    Call<PixabayResponse<Hit>> getImages(@Query("q") String keyWord, @Query("page") int page,
                                         @Query("per_page") int limit);

    @GET("/api/videos?key=26046390-cb448d9504b44ee5e839bc357")
    Call<MainVideoResponce> getVideo(@Query("q") String tags, @Query("page") int page,
                                     @Query("per_page") int limit);

}
