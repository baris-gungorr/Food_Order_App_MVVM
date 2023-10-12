package com.barisgungorr.data.datasource

import android.util.Log
import com.barisgungorr.data.entity.CRUDmeals
import com.barisgungorr.data.entity.Meals
import com.barisgungorr.data.entity.Sepetler
import com.barisgungorr.data.entity.Yemekler
import com.barisgungorr.ui.retrofit.HomeMealsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Field

class MealsDataSource(var mdao:HomeMealsDao) {
    suspend fun getMeals() : List<Yemekler> = withContext(Dispatchers.IO) {

        return@withContext mdao.getMeals().yemekler
    }

    suspend fun addMeals(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String) {
        val success  = mdao.addMeals(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
        Log.e("Meals Save","SUCCESFULLY : ${success.success} - Mesaj : ${success.message}")
    }

    suspend fun getBasketMeals(kullanici_adi: String) : List<Sepetler> = withContext(Dispatchers.IO) {
        val succes = mdao.getBasketMeals(kullanici_adi)
        return@withContext succes.yemekler
    }
}