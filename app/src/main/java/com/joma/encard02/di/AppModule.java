package com.joma.encard02.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.joma.encard02.data.network.pixabay.PixabayApi;
import com.joma.encard02.repositories.MainRepository;
import com.joma.encard02.ui.Prefs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@InstallIn(SingletonComponent.class)
@Module
public class AppModule {

    @Provides
    @Singleton
    public static PixabayApi providerRetrofit(OkHttpClient client) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return new Retrofit.Builder()
                .baseUrl("https://pixabay.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
                .create(PixabayApi.class);
    }

    @Provides
    @Singleton
    public static OkHttpClient providerClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    @Provides
    @Singleton
    public static MainRepository providerMainRepository(PixabayApi api) {
        return new MainRepository(api);
    }

    @Provides
    public SharedPreferences providerPrefs(@ApplicationContext Context context) {
        return context.getApplicationContext()
                .getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    @Provides
    public Prefs providerPref(SharedPreferences preferences) {
        return new Prefs(preferences);
    }

}
