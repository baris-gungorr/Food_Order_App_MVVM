package com.barisgungorr.bootcamprecipeapp.ui.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisgungorr.bootcamprecipeapp.data.entity.Sepetler
import com.barisgungorr.bootcamprecipeapp.data.repo.MealsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OrderViewModel @Inject constructor(private val mRepo: MealsRepository) : ViewModel() {
    var basketList: MutableLiveData<List<Sepetler>?> = MutableLiveData()
    var totalPrice = 0

    init {
        getOrder()
        getBasketMeals("BarisGungor")
    }

    private fun getBasketMeals(userName: String) {

        viewModelScope.launch {

            try {
                basketList.value = mRepo.getBasketMeals(userName)
                orderTotalPrice()
            } catch (e: Exception) {
                basketList.value = null
            }
        }
    }

    fun delete(card_meals_id: Int, userName: String) {
        viewModelScope.launch {
            mRepo.delete(userName, card_meals_id)
            getBasketMeals(userName)
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
                mRepo.getBasketMeals("BarisGungor")
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





