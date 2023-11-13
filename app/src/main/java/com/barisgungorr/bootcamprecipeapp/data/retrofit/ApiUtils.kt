package com.barisgungorr.bootcamprecipeapp.data.retrofit

import com.barisgungorr.bootcamprecipeapp.data.source.remote.HomeMealsApi
import com.barisgungorr.bootcamprecipeapp.utils.constans.AppConstants

class ApiUtils {
    companion object {

        fun getMealsDao(): HomeMealsApi {
            return RetrofitClient.getClient(AppConstants.BASE_URL).create(HomeMealsApi::class.java)
        }
    }
}