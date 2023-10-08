package com.barisgungorr.ui.retrofit

import com.barisgungorr.data.entity.Meals
import com.barisgungorr.utils.constants.Constans
import retrofit2.http.GET

interface HomeMealsDao {
    @GET(Constans.GET_FULL_MEALS)
    fun mealsGet() : Meals

}