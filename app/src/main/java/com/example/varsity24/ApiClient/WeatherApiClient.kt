package com.example.varsity24.ApiClient

import com.example.varsity24.Api.WeatherApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object WeatherApiClient {


        private const val BASE_URL = "http://dataservice.accuweather.com/forecasts/v1/daily/"


        private val client = OkHttpClient.Builder()
            .build()

        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        fun buildService(): WeatherApi {
            return retrofit.create(WeatherApi::class.java)
        }



}