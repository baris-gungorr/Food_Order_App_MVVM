package com.barisgungorr.ui.retrofit

import com.barisgungorr.data.entity.CRUDmeals
import com.barisgungorr.data.entity.Meals
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
    suspend fun addMeals( @Field("yemek_adi") meals_name:String,
                          @Field("yemek_resim_adi") meals_image_name:String,
                          @Field("yemek_fiyat") meals_price:Int,
                          @Field("yemek_siparis_adet") meals_order_piece:Int,
                          @Field("kullanici_adi") user_name:String) : CRUDmeals



}