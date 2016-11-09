package com.gaurav.accolitetest.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by gaurav on 7/11/16.
 */
public class StateListModel implements Serializable {

    @SerializedName("RestResponse")
    private RestResponse restResponse;

    public ArrayList<StateDataModel> getResult() {
        return restResponse.result;
    }

    private class RestResponse implements Serializable {

        @SerializedName("result")
        private ArrayList<StateDataModel> result;

        public ArrayList<StateDataModel> getResult() {
            return result;
        }

        public void setResult(ArrayList<StateDataModel> result) {
            this.result = result;
        }
    }
}
