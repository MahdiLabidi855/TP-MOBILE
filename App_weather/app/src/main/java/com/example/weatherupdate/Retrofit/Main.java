package com.example.weatherupdate.Retrofit;

import com.google.gson.annotations.SerializedName;

public class Main {

@SerializedName("temp")
    String temp ;
@SerializedName("humidity")
    String humidity ;
@SerializedName("feels_like")
    String feels_liek;

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getFeels_liek() {
        return feels_liek;
    }

    public void setFeels_liek(String feels_liek) {
        this.feels_liek = feels_liek;
    }
}
