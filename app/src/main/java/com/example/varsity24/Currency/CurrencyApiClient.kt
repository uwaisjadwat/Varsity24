package com.example.varsity24.Currency

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object CurrencyApiClient {
    private const val BASE_URL = "https://api.api-ninjas.com/v1/"

    private val client = OkHttpClient.Builder()
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun buildService(): CurrencyApi {
        return retrofit.create(CurrencyApi::class.java)
    }
}
