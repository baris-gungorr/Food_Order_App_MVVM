package com.barisgungorr.data.datasource

import com.barisgungorr.data.entity.Meals
import com.barisgungorr.data.entity.Yemekler
import com.barisgungorr.ui.retrofit.HomeMealsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MealsDataSource(var mdao:HomeMealsDao) {
    suspend fun getMeals() : List<Yemekler> = withContext(Dispatchers.IO) {
        return@withContext mdao.getMeals().yemekler
    }


}