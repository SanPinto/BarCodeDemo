package com.barcode.reader.barcodereaderapp.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sangeetha on 9/8/17.
 */

public class ApiClientUtil {

    public static String BASE_URL = "http://www.mocky.io/";
    private Retrofit mRetrofit;
    private static ApiClientUtil sInstance;

    private ApiClientUtil() {

    }

    public static ApiClientUtil getInstance() {
        if (sInstance == null) {
            sInstance = new ApiClientUtil();
        }
        return sInstance;
    }


    public Retrofit getApiClient() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
