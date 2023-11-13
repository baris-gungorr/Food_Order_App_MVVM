package com.barisgungorr.bootcamprecipeapp.data.entity

import com.google.gson.annotations.SerializedName

data class BasketResponse(
    @SerializedName("sepet_yemekler") var meals: List<Basket>,
    @SerializedName("success") var success: Int
)