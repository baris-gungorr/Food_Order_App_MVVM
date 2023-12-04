package com.barisgungorr.bootcamprecipeapp.data.repo

import com.barisgungorr.bootcamprecipeapp.data.datasource.MealsDataSource
import com.barisgungorr.bootcamprecipeapp.data.entity.Favorite
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.Basket
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.Meal
import javax.inject.Inject

class MealsRepository @Inject constructor(
    private val dataSource: MealsDataSource
) {

    suspend fun getMeals(): List<Meal> = dataSource.getMeals()

    suspend fun addMeals(
        mealsName: String,
        mealsImageName: String,
        mealsPrice: Int,
        mealsOrderPiece: Int,
        userName: String
    ) =
        dataSource.addMeals(mealsName, mealsImageName, mealsPrice, mealsOrderPiece, userName)

    suspend fun getMeals(userName: String): List<Basket> {
        return dataSource.getMeals(userName)
    }

    suspend fun delete(userName: String, cardMealsId: Int) =
        dataSource.delete(userName, cardMealsId)

    suspend fun save(mealsId: Int, mealsName: String, mealsImageName: String) =
        dataSource.save(mealsId, mealsName, mealsImageName)

    suspend fun getFavorites(): List<Favorite> = dataSource.getFavorites()

    suspend fun deleteFavorite(mealsId: Int) = dataSource.deleteFavorite(mealsId)

    suspend fun searchFavorite(searchKeyword: String): List<Favorite> = dataSource.searchFavorite(searchKeyword)
}
