package com.barisgungorr.ui.retrofit

class ApiUtils {
    companion object {
    val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getMealsDao(): HomeMealsApi {
            return RetrofitClient.getClient(BASE_URL).create(HomeMealsApi::class.java)
        }
    }
}