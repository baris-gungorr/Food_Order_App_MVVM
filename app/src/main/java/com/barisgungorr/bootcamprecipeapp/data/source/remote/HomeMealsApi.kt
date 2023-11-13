package com.barisgungorr.bootcamprecipeapp.data.source.remote

import com.barisgungorr.bootcamprecipeapp.data.entity.CRUDmeals
import com.barisgungorr.bootcamprecipeapp.data.entity.Meals
import com.barisgungorr.bootcamprecipeapp.data.entity.BasketResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface HomeMealsApi {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun getMeals(): Meals

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun addMeals(
        @Field("yemek_adi") yemek_adi: String,
        @Field("yemek_resim_adi") yemek_resim_adi: String,
        @Field("yemek_fiyat") yemek_fiyat: Int,
        @Field("yemek_siparis_adet") yemek_siparis_adet: Int,
        @Field("kullanici_adi") kullanici_adi: String
    ): CRUDmeals

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun getMeals(@Field("kullanici_adi") kullanici_adi: String): BasketResponse

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun delete(
        @Field("kullanici_adi") kullanici_adi: String,
        @Field("sepet_yemek_id") sepet_yemek_id: Int
    ): CRUDmeals

}