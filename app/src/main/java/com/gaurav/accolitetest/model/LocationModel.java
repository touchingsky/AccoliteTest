package com.gaurav.accolitetest.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by gaurav on 7/11/16.
 */
public class LocationModel implements Serializable {

    @SerializedName("RestResponse")
    private RestResponse restResponse;

    public double getLongitude() {
        return restResponse.getResult().longitude;
    }

    public double getLatitude() {
        return restResponse.getResult().latitude;
    }

    public String getCity() {
        return restResponse.result.city;
    }

    public String getState() {
        return restResponse.result.state;
    }

    public String getCountry() {
        return restResponse.result.country;
    }

    public String getNetwork() {
        return restResponse.result.network;
    }

    private class RestResponse implements Serializable {

        @SerializedName("result")
        private Result result;

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }

        private class Result implements Serializable {

            @SerializedName("longitude")
            private double longitude;

            @SerializedName("latitude")
            private double latitude;

            @SerializedName("city")
            private String city;

            @SerializedName("state")
            private String state;

            @SerializedName("country")
            private String country;

            @SerializedName("network")
            private String network;

            public String getNetwork() {
                return network;
            }

            public void setNetwork(String network) {
                this.network = network;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }
        }
    }
}
