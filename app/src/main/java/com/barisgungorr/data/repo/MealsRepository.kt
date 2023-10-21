package com.barisgungorr.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.barisgungorr.data.datasource.MealsDataSource
import com.barisgungorr.data.entity.Favorite
import com.barisgungorr.data.entity.Meals
import com.barisgungorr.data.entity.Sepetler
import com.barisgungorr.data.entity.Yemekler
import com.barisgungorr.room.FavoriteDao
import com.barisgungorr.ui.retrofit.HomeMealsDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MealsRepository @Inject constructor (var mDao: MealsDataSource,fdao:FavoriteDao) {

    suspend fun getMeals(): List<Yemekler> = mDao.getMeals()

    suspend fun addMeals(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String) =
        mDao.addMeals(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)

    suspend fun getBasketMeals(kullanici_adi: String) : List<Sepetler> = mDao.getBasketMeals(kullanici_adi)

    suspend fun search(searchKeyword:String) : List<Yemekler> = mDao.search(searchKeyword)

    suspend fun delete (kullanici_adi: String,sepet_yemek_id:Int) = mDao.delete(kullanici_adi, sepet_yemek_id)

    suspend fun save(yemek_id:Int,yemek_adi: String,yemek_resim_adi: String) = mDao.save (yemek_id,yemek_adi, yemek_resim_adi)

    suspend fun getFavorites(): List<Favorite> = mDao.getFavorites()

    suspend fun deleteF (yemek_id:Int) = mDao.deleteF(yemek_id)

    suspend fun searchF(searchKeyword:String) : List<Favorite> = mDao.searchF(searchKeyword)





}
