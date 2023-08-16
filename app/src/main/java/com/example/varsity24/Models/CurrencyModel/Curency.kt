package com.example.varsity24.Models.CurrencyModel

import com.google.gson.annotations.SerializedName


data class Curency (

    @SerializedName("new_amount"   ) var newAmount   : Double? = null,
    @SerializedName("new_currency" ) var newCurrency : String? = null,
    @SerializedName("old_currency" ) var oldCurrency : String? = null,
    @SerializedName("old_amount"   ) var oldAmount   : Int?    = null

)