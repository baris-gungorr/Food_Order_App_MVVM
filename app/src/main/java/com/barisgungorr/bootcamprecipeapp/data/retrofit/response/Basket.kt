package com.barisgungorr.bootcamprecipeapp.data.retrofit.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Basket(
    @SerializedName("sepet_yemek_id") var cardMealsId: Int,
    @SerializedName("yemek_adi") var mealsName: String,
    @SerializedName("yemek_resim_adi") var mealsImageName: String,
    @SerializedName("yemek_fiyat") var mealsPrice: Int,
    @SerializedName("yemek_siparis_adet") var mealsOrderPiece: Int,
    @SerializedName("kullanici_adi") var userName: String
) : Parcelable