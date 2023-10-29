package com.barisgungorr.data.datasource

import com.barisgungorr.data.entity.Favorite
import com.barisgungorr.data.entity.Sepetler
import com.barisgungorr.data.entity.Yemekler
import com.barisgungorr.room.FavoriteDao
import com.barisgungorr.ui.retrofit.HomeMealsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MealsDataSource(private val mdao:HomeMealsApi, private val fdao:FavoriteDao) {
    suspend fun getMeals() : List<Yemekler> = withContext(Dispatchers.IO) {
        return@withContext mdao.getMeals().yemekler
    }
    suspend fun addMeals(mealsName:String,meals_image_name:String,meals_price:Int,meals_order_price:Int,userName:String) {
                 mdao.addMeals(mealsName,meals_image_name,meals_price,meals_order_price,userName)
    }
    suspend fun getBasketMeals(userName: String) : List<Sepetler> = withContext(Dispatchers.IO) {
       val success = mdao.getBasketMeals(userName)
        return@withContext success.meals
    }

    suspend fun delete (userName: String,card_meals_id:Int) {
         mdao.delete(userName, card_meals_id)
    }
    suspend fun save(meals_id: Int,meals_name: String,meals_image_name: String) {
        val newFavorite = Favorite(meals_id, meals_name, meals_image_name)
        fdao.save(newFavorite)
    }
    suspend fun getFavorites() : List<Favorite>  = withContext(Dispatchers.IO) {

        return@withContext fdao.getFavorites()
    }
    suspend fun deleteF (meals_id:Int) {
        val deleteF = Favorite(meals_id,"","")
        fdao.deleteF(deleteF)
    }

    suspend fun searchF(searchKeyword:String) : List<Favorite> = withContext(Dispatchers.IO){
        return@withContext fdao.searchF(searchKeyword)
    }
}



