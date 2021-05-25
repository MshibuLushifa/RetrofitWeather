package com.mtlteam.retrofitweather.rest.service.model;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("currently")
    private Currently currently;

    public Currently getCurrentlyResponse() {
        return currently;
    }

    @Override
    public String toString() {
        return currently.toString();
    }
}
