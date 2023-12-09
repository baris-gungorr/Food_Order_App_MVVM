package com.barisgungorr.bootcamprecipeapp.data.retrofit.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealResponse(
    @SerializedName("yemek_id") val id: Int,
    @SerializedName("yemek_adi") val name: String,
    @SerializedName("yemek_resim_adi") val imageName: String,
    @SerializedName("yemek_fiyat") val price: Int
) : Parcelable
