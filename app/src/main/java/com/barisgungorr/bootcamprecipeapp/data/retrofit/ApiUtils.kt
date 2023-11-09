package com.barisgungorr.bootcamprecipeapp.data.retrofit

import com.barisgungorr.bootcamprecipeapp.data.source.remote.HomeMealsApi
import com.barisgungorr.bootcamprecipeapp.utils.constans.Constans

class ApiUtils {
    companion object {

        fun getMealsDao(): HomeMealsApi {
            return RetrofitClient.getClient(Constans.BASE_URL).create(HomeMealsApi::class.java)
        }
    }
}