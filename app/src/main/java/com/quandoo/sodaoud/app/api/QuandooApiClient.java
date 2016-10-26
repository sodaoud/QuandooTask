package com.quandoo.sodaoud.app.api;

import com.quandoo.sodaoud.app.model.Customer;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by sofiane on 10/24/16.
 */

public interface QuandooApiClient {

    @GET("customer-list.json")
    Observable<List<Customer>> getCustomers();

    @GET("table-map.json")
    Observable<List<Boolean>> getTables();


    class Factory {
        public static QuandooApiClient createApiClient() {
            return new Retrofit.Builder()
                    .baseUrl("https://s3-eu-west-1.amazonaws.com/quandoo-assessment/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build().create(QuandooApiClient.class);
        }
    }
}
