package com.barisgungorr.bootcamprecipeapp.ui.card

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.data.datasource.MealsRepository
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.BasketMealResponse
import com.barisgungorr.bootcamprecipeapp.utils.constans.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CardViewModel @Inject constructor(private val mealsRepository: MealsRepository) : ViewModel() {

    var basketList: MutableLiveData<List<BasketMealResponse>?> = MutableLiveData()
    private var totalPrice = 0

    val message = MutableSharedFlow<Int>()
    val navigateMainScreen = MutableSharedFlow<Boolean>()

    fun getBasketMeals() {
        viewModelScope.launch {
            try {
                val baskets = mealsRepository.getMeals(AppConstants.USERNAME)
                basketList.value = baskets
                calculateTotalPrice(baskets)
            } catch (e: Exception) {
                basketList.value = null
            }
        }
    }

    private fun calculateTotalPrice(baskets: List<BasketMealResponse>) {
        var total = 0
        baskets.forEach { orders ->
            total += orders.piece * orders.price
        }
        totalPrice = total
    }

    fun decreaseOrderQuantity(basket: BasketMealResponse) {
        if (basket.piece > 1) {
            basket.piece.dec()
            // Update basket object with using Api
        }
    }

    fun increaseOrderQuantity(basket: BasketMealResponse) {
        basket.piece.inc()
        // Update basket object with using Api
    }

    fun delete(mealId: Int) {
        viewModelScope.launch {
            mealsRepository.delete(userName = AppConstants.USERNAME, cardMealsId = mealId)
            getBasketMeals()
            orderLastItem()
        }
    }

    private fun orderLastItem() {
        if (!basketList.value.isNullOrEmpty()) {
            val lastItem = basketList.value!!.toMutableList()
            lastItem.removeAt(lastItem.lastIndex)
            basketList.value = lastItem
            totalPrice = lastItem.sumOf { it.price * it.piece }
        }
    }


    fun completeOrders() {
        val isBasketEmpty = basketList.value.isNullOrEmpty()
        if (isBasketEmpty) {
            sendMessage(R.string.card_page_add_product_card)
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





