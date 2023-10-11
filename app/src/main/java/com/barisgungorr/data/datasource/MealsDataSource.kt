package com.barisgungorr.data.datasource

import android.util.Log
import com.barisgungorr.data.entity.Meals
import com.barisgungorr.data.entity.Yemekler
import com.barisgungorr.ui.retrofit.HomeMealsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MealsDataSource(var mdao:HomeMealsDao) {
    suspend fun getMeals() : List<Yemekler> = withContext(Dispatchers.IO) {

        return@withContext mdao.getMeals().yemekler
    }

    suspend fun addMeals(mealsName:String,meals_image_name:String,meals_price:Int,meals_order_piece:Int,user_name:String) {
        val success  = mdao.addMeals(mealsName,meals_image_name,meals_price,meals_order_piece,user_name)
        Log.e("Meals Save","SUCCESFULLY : ${success.success} - Mesaj : ${success.message}")


    }






}