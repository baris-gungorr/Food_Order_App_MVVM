package com.barisgungorr.bootcamprecipeapp.data.datasource

import com.barisgungorr.bootcamprecipeapp.data.entity.Favorite
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.Basket
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.Meal
import com.barisgungorr.bootcamprecipeapp.data.source.locale.FavoriteDao
import com.barisgungorr.bootcamprecipeapp.data.source.remote.HomeMealsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MealsDataSource(
    private val api: HomeMealsApi,
    private val favoriteDao: FavoriteDao
) {
    suspend fun getMeals(): List<Meal> = withContext(Dispatchers.IO) {
        return@withContext api.getMeals().yemekler
    }

    suspend fun addMeals(
        mealsName: String,
        mealsImageName: String,
        mealsPrice: Int,
        mealsOrderPrice: Int,
        userName: String
    ) {
        api.addMeals(mealsName, mealsImageName, mealsPrice, mealsOrderPrice, userName)
    }

    suspend fun getMeals(userName: String): List<Basket> = withContext(Dispatchers.IO) {
        val success = api.getMeals(userName)
        return@withContext success.meals
    }

    suspend fun delete(userName: String, cardMealsId: Int) {
        api.delete(userName, cardMealsId)
    }

    suspend fun save(mealsId: Int, mealsName: String, mealsImageName: String) {
        val newFavorite = Favorite(mealsId, mealsName, mealsImageName)
        favoriteDao.save(newFavorite)
    }

    suspend fun getFavorites(): List<Favorite> = withContext(Dispatchers.IO) {
        return@withContext favoriteDao.getFavorites()
    }

    suspend fun deleteFavorite(mealsId: Int) {
        val deleteFavorite = Favorite(mealsId, "", "")
        favoriteDao.deleteFavorite(deleteFavorite)
    }

    suspend fun searchFavorite(searchKeyword: String): List<Favorite> = withContext(Dispatchers.IO) {
        return@withContext favoriteDao.searchF(searchKeyword)
    }
}



