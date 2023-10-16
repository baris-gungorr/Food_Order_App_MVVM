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
        Log.e("GetBasketMeals", "SUCCESFULLY GetBasketMeals")
        return@withContext succes.yemekler
    }

    suspend fun search(searchKeyword:String) : List<Yemekler> = withContext(Dispatchers.IO){
       val list = ArrayList<Yemekler>()

       val m1 = Yemekler("Ayran",3,1,"ayran.png")
       // val m2 = Yemekler("Baklava",25,2,"baklava.png")
       // val m3 = Yemekler("Fanta",6,3,"fanta.png")
         //    val m4 = Yemekler("Izgara somon",55,4,"izgarasomon.png")
       // val m5 = Yemekler("Izgara tavuk",27,5,"izgaratavuk.png")
         //    val m6 = Yemekler("Kadayıf",22,6,"kadayif.png")
       // val m7 = Yemekler("Kahve",16,7,"kahve.png")
         //   val m8 = Yemekler("Köfte",25,8,"kofte.png")
       // val m9 = Yemekler("Lazanya",32,9,"lazanya.png")
         //   val m10 = Yemekler("Makarna",28,10,"makarna.png")
        //val m11 = Yemekler("Su",2,11,"su.png")
          //  val m12 = Yemekler("Sütlaç",10,12,"sutlac.png")
        //val m13 = Yemekler("Köfte",25,13,"kofte.png")
          //  val m14 = Yemekler("Tiramisu",23,14,"tiramisu.png")

        list.add(m1)
       //     list.add(m2)
        //list.add(m3)
        //    list.add(m4)
      //  list.add(m5)
      //      list.add(m6)
     //   list.add(m7)
        //    list.add(m8)
     //   list.add(m9)
     //       list.add(m10)
      //  list.add(m11)
      //       list.add(m12)
      //  list.add(m13)
       //      list.add(m14)

        return@withContext list

    }

    suspend fun delete (kullanici_adi: String,sepet_yemek_id:Int) {
        val success = mdao.delete(kullanici_adi, sepet_yemek_id)

        Log.e("Meals Delete" , "Success : ${success.success}  - Message: ${success.message}")

    }
}



