package com.barisgungorr.bootcamprecipeapp.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Sepetler(
    @SerializedName("sepet_yemek_id") var card_meals_id: Int,
    @SerializedName("yemek_adi") var meals_name: String,
    @SerializedName("yemek_resim_adi") var meals_image_name: String,
    @SerializedName("yemek_fiyat") var meals_price: Int,
    @SerializedName("yemek_siparis_adet") var meals_order_piece: Int,
    @SerializedName("kullanici_adi") var user_name: String
) : Serializable {

}