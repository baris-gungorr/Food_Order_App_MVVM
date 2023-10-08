package com.barisgungorr.ui.retrofit

import com.barisgungorr.data.entity.Meals
import com.barisgungorr.utils.constants.Constans
import retrofit2.http.GET

interface HomeMealsDao {
   // @GET(Constans.GET_FULL_MEALS)
    //fun mealsGet() : Meals

    // http://kasimadalan.pe.hu/ -> BASE_URL
        // yemekler/tumYemekleriGetir.php ->apiUrl

    @GET("yemekler/tumYemekleriGetir.php")
     fun getMeals() : Meals

}