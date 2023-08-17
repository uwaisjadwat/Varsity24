package com.example.varsity24.Weather

import com.example.varsity24.Models.WeatherModel.Weather
import io.reactivex.Observable
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;




interface WeatherApi {


    // Define the endpoint and request method
    @GET("1day/305605")
    fun getWeatherData(@Query("apikey") apiKey: String
    ): Observable<Weather>
}