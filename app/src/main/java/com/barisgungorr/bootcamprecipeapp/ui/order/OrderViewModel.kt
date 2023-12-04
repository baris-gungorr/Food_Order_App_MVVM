package com.barisgungorr.bootcamprecipeapp.ui.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.Basket
import com.barisgungorr.bootcamprecipeapp.data.repo.MealsRepository
import com.barisgungorr.bootcamprecipeapp.utils.constans.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OrderViewModel @Inject constructor(private val mRepo: MealsRepository) : ViewModel() {

    var basketList: MutableLiveData<List<Basket>?> = MutableLiveData()
    private var totalPrice = 0

    val message = MutableSharedFlow<Int>()
    val navigateMainScreen = MutableSharedFlow<Boolean>()

    fun getBasketMeals() {
        viewModelScope.launch {
            try {
                val baskets = mRepo.getMeals(AppConstants.USERNAME)
                basketList.value = baskets
                calculateTotalPrice(baskets)
            } catch (e: Exception) {
                basketList.value = null
            }
        }
    }

    private fun calculateTotalPrice(baskets: List<Basket>) {
        var total = 0
        baskets.forEach { orders ->
            total += orders.mealsOrderPiece * orders.mealsPrice
        }
        totalPrice = total
    }

    fun decreaseOrderQuantity(basket: Basket) {
        if (basket.mealsOrderPiece > 1) {
            basket.mealsOrderPiece.dec()
            // Update basket object with using Api
        }
    }

    fun increaseOrderQuantity(basket: Basket) {
        basket.mealsOrderPiece.inc()
        // Update basket object with using Api
    }

    fun delete(mealId: Int) {
        viewModelScope.launch {
            mRepo.delete(userName = AppConstants.USERNAME, cardMealsId = mealId)
            getBasketMeals()
            orderLastItem()
        }
    }

    private fun orderLastItem() {
        if (!basketList.value.isNullOrEmpty()) {
            val lastItem = basketList.value!!.toMutableList()
            lastItem.removeAt(lastItem.lastIndex)
            basketList.value = lastItem
            totalPrice = lastItem.sumOf { it.mealsPrice * it.mealsOrderPiece }
        }
    }


    fun completeOrders() {
        val isBasketEmpty = basketList.value.isNullOrEmpty()
        if (isBasketEmpty) {
            sendMessage(R.string.addProductCard)
        } else {
            navigateToMainScreen()
        }
    }

    private fun navigateToMainScreen() {
        navigateMainScreen.tryEmit(true)
    }

    private fun sendMessage(messageResId: Int) {
        message.tryEmit(messageResId)
    }

}





