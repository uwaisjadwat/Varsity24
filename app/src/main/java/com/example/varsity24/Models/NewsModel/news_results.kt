package com.example.varsity24.Models.NewsModel

import com.google.gson.annotations.SerializedName

data class news_results(

    @SerializedName("status"       ) var status       : String?             = null,
    @SerializedName("totalResults" ) var totalResults : Int?                = null,
    @SerializedName("articles"     ) var articles     : ArrayList<Articles> = arrayListOf()


)
