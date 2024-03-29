package com.barisgungorr.bootcamprecipeapp.data.repository

import android.util.Log
import com.barisgungorr.bootcamprecipeapp.data.entity.FavoriteEntity
import com.barisgungorr.bootcamprecipeapp.data.entity.toMeal
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.BasketMealResponse
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.MealResponse
import com.barisgungorr.bootcamprecipeapp.data.source.locale.FavoriteDao
import com.barisgungorr.bootcamprecipeapp.data.source.remote.ApiService
import com.barisgungorr.bootcamprecipeapp.domain.FavoriteMeal
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

    suspend fun save(mealId: Int, mealName: String, mealImage: String) {
        val newFavorite = FavoriteEntity(mealId, mealName, mealImage)
        favoriteDao.save(newFavorite)
        Log.e("Save", "Character saved successfully: $newFavorite")
    }

    suspend fun getFavorites(): List<FavoriteMeal> = withContext(Dispatchers.IO) {
        return@withContext favoriteDao.getFavorites().map { entity -> entity.toMeal() }
    }

    suspend fun deleteFavorite(mealsId: Int) {
        val deleteFavorite = FavoriteEntity(mealsId, "", "")
        favoriteDao.deleteFavorite(deleteFavorite)
    }

    suspend fun searchFavorite(searchKeyword: String): List<FavoriteMeal> =
        withContext(Dispatchers.IO) {
            return@withContext favoriteDao.searchFavorite(searchKeyword)
                .map { entity -> entity.toMeal() }
        }
}



