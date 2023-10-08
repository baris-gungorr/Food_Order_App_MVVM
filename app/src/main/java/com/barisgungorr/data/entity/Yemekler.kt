package com.barisgungorr.data.entity

import java.io.Serializable

data class Yemekler(
    val yemek_adi: String,
    val yemek_fiyat: String,
    val yemek_id: String,
    val yemek_resim_adi: String) : Serializable {

    }