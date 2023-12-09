package com.barisgungorr.bootcamprecipeapp.data.datasource

import com.barisgungorr.bootcamprecipeapp.data.entity.Favorite
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.BasketMealResponse
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.MealResponse
import com.barisgungorr.bootcamprecipeapp.data.source.locale.FavoriteDao
import com.barisgungorr.bootcamprecipeapp.data.source.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MealsRepository(
    private val service: ApiService,
    private val favoriteDao: FavoriteDao
) {
    suspend fun getMeals(): List<MealResponse> = withContext(Dispatchers.IO) {
        return@withContext service.getMeals().yemekler
    }

    suspend fun addMeals(
        mealsName: String,
        mealsImageName: String,
        mealsPrice: Int,
        mealsOrderPrice: Int,
        userName: String
    ) {
        service.addMeals(mealsName, mealsImageName, mealsPrice, mealsOrderPrice, userName)
    }

    suspend fun getMeals(userName: String): List<BasketMealResponse> = withContext(Dispatchers.IO) {
        val success = service.getMeals(userName)
        return@withContext success.meals
    }

    suspend fun delete(userName: String, cardMealsId: Int) {
        service.delete(userName, cardMealsId)
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
        return@withContext favoriteDao.searchFavorite(searchKeyword)
    }
}



