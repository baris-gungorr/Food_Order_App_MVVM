package com.barisgungorr.ui.retrofit

import com.barisgungorr.utils.constants.Constans.BASE_URL

class ApiUtils {
    companion object {

        fun mealsDao(): HomeMealsDao {
            return RetrofitClient.getClient(BASE_URL).create(HomeMealsDao::class.java)

        }
    }
}