package com.example.varsity24.Currency

import io.reactivex.Observable
import com.example.varsity24.Models.CurrencyModel.Curency
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Currency

interface CurrencyApi {

    @GET("convertcurrency")
    fun getCurrency(@Query("have") have : String,
                    @Query("want") want : String,
                    @Query("amount") amount : String,
    ): Observable<Curency>
}