package com.example.varsity24.Models.WeatherModel

import com.google.gson.annotations.SerializedName


data class Maximum (

    @SerializedName("Value"    ) var Value    : Int?    = null,
    @SerializedName("Unit"     ) var Unit     : String? = null,
    @SerializedName("UnitType" ) var UnitType : Int?    = null

)