package com.barisgungorr.data.repo

import com.barisgungorr.data.datasource.MealsDataSource
import com.barisgungorr.data.entity.Favorite
import com.barisgungorr.data.entity.Sepetler
import com.barisgungorr.data.entity.Yemekler
import javax.inject.Inject

class MealsRepository @Inject constructor (private val mDao: MealsDataSource) {

    suspend fun getMeals(): List<Yemekler> = mDao.getMeals()

    suspend fun addMeals(meals_name:String,meals_image_name:String,meals_price:Int,meals_order_piece:Int,userName:String) =
        mDao.addMeals(meals_name, meals_image_name , meals_price,meals_order_piece, userName)

    suspend fun getBasketMeals(userName: String) : List<Sepetler> = mDao.getBasketMeals(userName)

    suspend fun delete (userName: String,card_meals_id:Int) = mDao.delete(userName, card_meals_id)

    suspend fun save(meals_id:Int,meals_name: String,meals_image_name: String) = mDao.save (meals_id,meals_name,meals_image_name)

    suspend fun getFavorites(): List<Favorite> = mDao.getFavorites()

    suspend fun deleteF (meals_id:Int) = mDao.deleteF(meals_id)

    suspend fun searchF(searchKeyword:String) : List<Favorite> = mDao.searchF(searchKeyword)
}
