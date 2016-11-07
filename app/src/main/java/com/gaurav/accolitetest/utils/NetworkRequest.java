package com.gaurav.accolitetest.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gaurav on 6/11/16.
 */

public class NetworkRequest {

    public interface Request {

    }

    private Request request = new Retrofit.Builder()
            .baseUrl("http://")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Request.class);
}
