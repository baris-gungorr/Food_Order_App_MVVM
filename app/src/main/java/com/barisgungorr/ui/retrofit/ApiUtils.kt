package com.barisgungorr.ui.retrofit

import com.barisgungorr.data.source.remote.HomeMealsApi
import retrofit2.Retrofit

class ApiUtils {
  /*

   */
    companion object {
        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getMealsDao(): HomeMealsApi {
            return RetrofitClient.getClient(BASE_URL).create(HomeMealsApi::class.java)
        }
    }
}