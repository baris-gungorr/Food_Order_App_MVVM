package com.barisgungorr.utils.constants

import retrofit2.http.DELETE

object Constans {
  const  val BASE_URL = "http://kasimadalan.pe.hu/yemekler/"

  const val GET_MEALS = "tumYemekleriGetir.php"
  const val ADD_MEALS = "yemekler/sepeteYemekEkle.php"
  const val GET_BASKET_MEALS = "yemekler/sepettekiYemekleriGetir.php"
  const val DELETE = "yemekler/sepettenYemekSil.php"


}