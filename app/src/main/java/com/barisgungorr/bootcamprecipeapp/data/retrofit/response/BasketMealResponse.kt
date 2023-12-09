package com.barisgungorr.bootcamprecipeapp.data.retrofit.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BasketMealResponse(
    @SerializedName("sepet_yemek_id") val id: Int,
    @SerializedName("yemek_adi") val name: String,
    @SerializedName("yemek_resim_adi") val imageName: String,
    @SerializedName("yemek_fiyat") val price: Int,
    @SerializedName("yemek_siparis_adet") val piece: Int,
    @SerializedName("kullanici_adi") val userName: String
) : Parcelable