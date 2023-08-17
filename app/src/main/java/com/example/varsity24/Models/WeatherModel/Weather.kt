package com.example.varsity24.Models.WeatherModel

import com.google.gson.annotations.SerializedName

data class Weather(

    @SerializedName("Headline"       ) var Headline       : Headline?                 = Headline(),
    @SerializedName("DailyForecasts" ) var DailyForecasts : ArrayList<DailyForecasts> = arrayListOf()

)
