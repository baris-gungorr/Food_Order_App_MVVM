package com.barisgungorr.bootcamprecipeapp.data.datasource

import com.barisgungorr.bootcamprecipeapp.data.entity.Favorite
import com.barisgungorr.bootcamprecipeapp.data.entity.Sepetler
import com.barisgungorr.bootcamprecipeapp.data.entity.Yemekler
import com.barisgungorr.bootcamprecipeapp.data.source.locale.FavoriteDao
import com.barisgungorr.bootcamprecipeapp.data.source.remote.HomeMealsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MealsDataSource(private val mDao: HomeMealsApi, private val fDao: FavoriteDao) {
    suspend fun getMeals() : List<Yemekler> = withContext(Dispatchers.IO) {
        return@withContext mDao.getMeals().yemekler
    }
    suspend fun addMeals(mealsName:String,meals_image_name:String,meals_price:Int,meals_order_price:Int,userName:String) {
                 mDao.addMeals(mealsName,meals_image_name,meals_price,meals_order_price,userName)
    }
    suspend fun getBasketMeals(userName: String) : List<Sepetler> = withContext(Dispatchers.IO) {
       val success = mDao.getBasketMeals(userName)
        return@withContext success.meals
    }

    suspend fun delete (userName: String,card_meals_id:Int) {
         mDao.delete(userName, card_meals_id)
    }
    suspend fun save(meals_id: Int,meals_name: String,meals_image_name: String) {
        val newFavorite = Favorite(meals_id, meals_name, meals_image_name)
        fDao.save(newFavorite)
    }
    suspend fun getFavorites() : List<Favorite>  = withContext(Dispatchers.IO) {

        return@withContext fDao.getFavorites()
    }
    suspend fun deleteF (meals_id:Int) {
        val deleteF = Favorite(meals_id,"","")
        fDao.deleteF(deleteF)
    }

    suspend fun searchF(searchKeyword:String) : List<Favorite> = withContext(Dispatchers.IO){
        return@withContext fDao.searchF(searchKeyword)
    }
}



