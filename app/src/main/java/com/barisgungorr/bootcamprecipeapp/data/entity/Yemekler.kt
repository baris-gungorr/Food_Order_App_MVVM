package com.barisgungorr.bootcamprecipeapp.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Yemekler(
    @SerializedName("yemek_adi") val meals_name: String,
    @SerializedName("yemek_fiyat") val meals_price: Int,
    @SerializedName("yemek_id") val meals_id: Int,
    @SerializedName("yemek_resim_adi") val meals_image_name: String
) : Serializable {

}