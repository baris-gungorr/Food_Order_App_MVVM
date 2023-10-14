package com.barisgungorr.ui.retrofit

import com.barisgungorr.data.entity.CRUDmeals
import com.barisgungorr.data.entity.Meals
import com.barisgungorr.data.entity.SepetlerCevap
import com.barisgungorr.utils.constants.Constans
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface HomeMealsDao {
   // @GET(Constans.GET_FULL_MEALS)
    //fun mealsGet() : Meals

    // http://kasimadalan.pe.hu/ -> BASE_URL
        // yemekler/tumYemekleriGetir.php ->apiUrl

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun getMeals() : Meals

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun addMeals( @Field("yemek_adi") yemek_adi:String,
                          @Field("yemek_resim_adi") yemek_resim_adi:String,
                          @Field("yemek_fiyat") yemek_fiyat :Int,
                          @Field("yemek_siparis_adet") yemek_siparis_adet:Int,
                          @Field("kullanici_adi") kullanici_adi:String) : CRUDmeals

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun getBasketMeals( @Field("kullanici_adi") kullanici_adi: String) : SepetlerCevap


}