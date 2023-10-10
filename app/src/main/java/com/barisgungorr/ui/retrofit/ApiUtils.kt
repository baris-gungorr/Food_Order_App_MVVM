package com.barisgungorr.ui.retrofit

import com.barisgungorr.utils.constants.Constans.BASE_URL

class ApiUtils {
    companion object {
    val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getMealsDao(): HomeMealsDao {
            return RetrofitClient.getClient(BASE_URL).create(HomeMealsDao::class.java)
        }
    }
}