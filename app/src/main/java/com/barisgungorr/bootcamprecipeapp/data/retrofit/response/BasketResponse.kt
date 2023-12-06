package com.barisgungorr.bootcamprecipeapp.data.retrofit.response

import com.google.gson.annotations.SerializedName

data class BasketResponse(
    @SerializedName("sepet_yemekler") val meals: List<Basket>,
    @SerializedName("success") val success: Int
)