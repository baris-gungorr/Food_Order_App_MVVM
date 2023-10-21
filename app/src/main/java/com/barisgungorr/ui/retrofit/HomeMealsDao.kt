package com.barisgungorr.ui.retrofit

import com.barisgungorr.data.entity.CRUDmeals
import com.barisgungorr.data.entity.FavoriteSuccess
import com.barisgungorr.data.entity.Meals
import com.barisgungorr.data.entity.SepetlerCevap
import com.barisgungorr.utils.constants.Constans
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface HomeMealsDao {

    // http://kasimadalan.pe.hu/ -> BASE_URL


  //  @GET("yemekler/tumYemekleriGetir.php")

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

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun delete(@Field("kullanici_adi") kullanici_adi: String,
                       @Field("sepet_yemek_id") sepet_yemek_id:Int) : CRUDmeals

    suspend fun save(@Field("yemek_id") yemek_id:Int,
                     @Field("yemek_adi") yemek_adi: String,
                     @Field("yemek_resim_adi") yemek_resim_adi: String) :FavoriteSuccess




}