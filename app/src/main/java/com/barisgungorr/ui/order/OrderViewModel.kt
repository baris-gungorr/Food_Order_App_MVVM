package com.barisgungorr.ui.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisgungorr.data.entity.Sepetler
import com.barisgungorr.data.repo.MealsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OrderViewModel @Inject constructor(private val mrepo: MealsRepository) : ViewModel() {
    var basketList: MutableLiveData<List<Sepetler>?> = MutableLiveData()

    var totalPrice = 0

    init {
        getOrder()
        getBasketMeals("BarisGungor")
    }

    private fun getBasketMeals(kullanici_adi: String) {

        viewModelScope.launch {

            try {
                basketList.value = mrepo.getBasketMeals(kullanici_adi)
                orderTotalPrice()
            } catch (e: Exception) {
                basketList.value = null
            }
        }
    }

    fun delete(sepet_yemek_id: Int, kullanici_adi: String) {
        viewModelScope.launch {
            mrepo.delete(kullanici_adi, sepet_yemek_id)
            getBasketMeals(kullanici_adi)
            orderLastItem()
        }
    }

    fun orderTotalPrice(): String {
        var total = 0
        basketList.value?.forEach { orders ->
            total += orders.meals_order_piece * orders.meals_price

        }
        totalPrice = total
        return totalPrice.toString()
    }

    fun getOrder() {
        viewModelScope.launch {
            try {
                mrepo.getBasketMeals("BarisGungor")
                orderTotalPrice()
            } catch (e: Exception) {

            }
        }
    }

    private fun orderLastItem() {
        if (!basketList.value.isNullOrEmpty()) {
            val lastItem = basketList.value!!.toMutableList()
            lastItem.removeAt(lastItem.lastIndex)
            basketList.value = lastItem
            totalPrice = lastItem.sumOf { it.meals_price * it.meals_order_piece }
        }
    }

    fun clearBasket() {
        basketList.value = mutableListOf()
    }

}





