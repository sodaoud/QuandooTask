package com.quandoo.sodaoud.app.di.modules;

import com.quandoo.sodaoud.app.api.QuandooApiClient;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sofiane on 10/24/16.
 */

//@Module
public class ApiClientModule {

//    @Provides
    public QuandooApiClient providesApi(){
        return new Retrofit.Builder()
                .baseUrl("https://s3-eu-west-1.amazonaws.com/quandoo-assessment/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build().create(QuandooApiClient.class);
    }
}
