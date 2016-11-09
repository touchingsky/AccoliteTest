package com.gaurav.accolitetest.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by gaurav on 9/11/16.
 */
public class StateDataModel implements Serializable {

    @SerializedName("name")
    private String name;

    @SerializedName("abbr")
    private String abbreviation;

    @SerializedName("capital")
    private String capital;

    @SerializedName("area")
    private String area;

    @SerializedName("largest_city")
    private String largestCity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLargestCity() {
        return largestCity;
    }

    public void setLargestCity(String largestCity) {
        this.largestCity = largestCity;
    }
}
