package com.example.varsity24.Api

import io.reactivex.Observable
import com.example.varsity24.Models.NewsModel.Articles
import com.example.varsity24.Models.NewsModel.news_results
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Currency

interface NewsApi {

    @GET("top-headlines")
    fun getNews(
        @Query("country") country: String, // The search query, in this case, "Durban"
        @Query("apiKey") apiKey: String // Your News API key
    ): Observable<news_results>
}