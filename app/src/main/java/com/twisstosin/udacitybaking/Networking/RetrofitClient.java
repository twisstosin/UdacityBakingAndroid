package com.twisstosin.udacitybaking.Networking;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofitUdactiy = null;

    public static Retrofit getUdacityClient(String baseUrl) {
        if (retrofitUdactiy==null) {
            retrofitUdactiy = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofitUdactiy;
    }
}
