package com.barisgungorr.data.datasource

import android.util.Log
import com.barisgungorr.data.entity.CRUDmeals
import com.barisgungorr.data.entity.Favorite
import com.barisgungorr.data.entity.Meals
import com.barisgungorr.data.entity.Sepetler
import com.barisgungorr.data.entity.Yemekler
import com.barisgungorr.room.FavoriteDao
import com.barisgungorr.ui.retrofit.HomeMealsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Field

class MealsDataSource(var mdao:HomeMealsDao,var fdao:FavoriteDao) {
    suspend fun getMeals() : List<Yemekler> = withContext(Dispatchers.IO) {
        return@withContext mdao.getMeals().yemekler
    }
    suspend fun addMeals(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String) {
        val success  = mdao.addMeals(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
        Log.e("Meals Save","SUCCESFULLY : ${success.success} - Mesaj : ${success.message}")
    }
    suspend fun getBasketMeals(kullanici_adi: String) : List<Sepetler> = withContext(Dispatchers.IO) {
        val succes = mdao.getBasketMeals(kullanici_adi)
        Log.e("GetBasketMeals", "SUCCESFULLY GetBasketMeals")
        return@withContext succes.yemekler
    }
    suspend fun search(searchKeyword:String) : List<Yemekler> = withContext(Dispatchers.IO){
       val list = ArrayList<Yemekler>()

         val m1 = Yemekler("Ayran",3,1,"ayran.png")
        list.add(m1)
        return@withContext list
    }
    suspend fun delete (kullanici_adi: String,sepet_yemek_id:Int) {
        val success = mdao.delete(kullanici_adi, sepet_yemek_id)

        Log.e("Meals Delete" , "Success : ${success.success}  - Message: ${success.message}")

    }
    suspend fun save(yemek_id: Int,yemek_adi: String,yemek_resim_adi: String) {
        val newFavorite = Favorite(yemek_id,yemek_adi, yemek_resim_adi)
      fdao.save(newFavorite)
    }
    suspend fun getFavorites() : List<Favorite>  = withContext(Dispatchers.IO) {

        return@withContext fdao.getFavorites()
    }
    suspend fun deleteF (yemek_id:Int) {
        val deleteF = Favorite(yemek_id,"","")
        fdao.deleteF(deleteF)
    }

    suspend fun searchF(searchKeyword:String) : List<Favorite> = withContext(Dispatchers.IO){

        return@withContext fdao.searchF(searchKeyword)
    }


}



