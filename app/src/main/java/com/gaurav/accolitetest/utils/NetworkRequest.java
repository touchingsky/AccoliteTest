package com.gaurav.accolitetest.utils;

import com.gaurav.accolitetest.model.LocationModel;
import com.gaurav.accolitetest.model.StateListModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by gaurav on 6/11/16.
 */

public class NetworkRequest {

    private static Retrofit.Builder requestBuilder = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create());

    public static Call<LocationModel> searchLocation(String searchString) {
        return requestBuilder.baseUrl("http://geo.groupkt.com/").build().create(Request.class).searchLocation(searchString);
    }

    public static Call<StateListModel> searchForState(String searchString) {
        return requestBuilder.baseUrl("http://services.groupkt.com/state/search/").build().create(Request.class)
                .searchForState(searchString);
    }

    interface Request {

        @GET("ip/{ip_address}/json")
        Call<LocationModel> searchLocation(@Path("ip_address") String searchString);

        @GET("IND")
        Call<StateListModel> searchForState(@Query("text") String searchString);
    }
}
