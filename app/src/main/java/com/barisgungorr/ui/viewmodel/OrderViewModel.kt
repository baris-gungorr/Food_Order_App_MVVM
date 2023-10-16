package com.barisgungorr.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barisgungorr.data.entity.Sepetler
import com.barisgungorr.data.repo.MealsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OrderViewModel @Inject constructor(var mrepo: MealsRepository): ViewModel() {
    var basketList : MutableLiveData<List<Sepetler>> = MutableLiveData()
    var totalPrice = 0

    init {
    getOrder()
       getBasketMeals("BarisGungor")

    }

    fun getBasketMeals(kullanici_adi:String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                basketList.value = mrepo.getBasketMeals(kullanici_adi)
                orderTotalPrice()
            }catch (e:Exception) {

            }
        }

    }

    fun delete(sepet_yemek_id: Int, kullanici_adi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            mrepo.delete(kullanici_adi, sepet_yemek_id)
            getBasketMeals(kullanici_adi)
            orderLastItem()

        }

    }

    fun orderTotalPrice(): String{
        var total = 0
        basketList.value?.forEach {
            total += it.yemek_siparis_adet * it.yemek_fiyat

        }
       totalPrice = total
        return totalPrice.toString()
    }
    fun getOrder() {
        CoroutineScope(Dispatchers.Main).launch {
            mrepo.getBasketMeals("BarisGungor")
            orderTotalPrice()
        }
    }

    fun orderLastItem() {
        if (!basketList.value.isNullOrEmpty()) {
            val lastItem = basketList.value!!.toMutableList()
            lastItem.removeAt(lastItem.lastIndex)
            basketList.value = lastItem
            totalPrice = lastItem.sumOf { it.yemek_fiyat * it.yemek_siparis_adet }
        }


    }
    fun clearBasket() {
        basketList.value = mutableListOf()
    }



}
