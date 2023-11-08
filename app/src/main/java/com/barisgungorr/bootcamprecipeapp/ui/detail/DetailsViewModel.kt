package com.barisgungorr.bootcamprecipeapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisgungorr.bootcamprecipeapp.data.entity.Sepetler
import com.barisgungorr.bootcamprecipeapp.data.repo.MealsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(private val mRepo: MealsRepository) : ViewModel() {
    private var basketList: MutableLiveData<List<Sepetler>> = MutableLiveData()
    var piece = MutableLiveData<Int>()

    init {
        getBasketMeals()
        piece.value = 1
    }
    fun buttonMinus() {
        val currentPiece = piece.value ?: 1
        if (currentPiece > 1) {
            piece.value = currentPiece - 1
        }
    }

    fun buttonPlus() {
        val currentPiece = piece.value ?: 1
        piece.value = currentPiece + 1
    }


    fun addMeals(
        meals_name: String, meals_image_name: String, meals_price: Int, meals_order_piece: Int, userName: String
    ) {
        viewModelScope.launch {
            try {
                mRepo.addMeals(meals_name, meals_image_name, meals_price, meals_order_piece, userName)

            } catch (e: Exception) {

            }
        }
    }

    private fun getBasketMeals() {

        viewModelScope.launch {
            try {
                basketList.value = mRepo.getBasketMeals("BarisGungor")

            } catch (e: Exception) {

            }
        }
    }

    fun save(meals_id: Int, meals_name: String, meals_image_name: String) {
        viewModelScope.launch {
            mRepo.save(meals_id, meals_name, meals_image_name)
        }
    }

    fun isProductInBasket(productName: String): Boolean {
        val basketItems = basketList.value ?: emptyList()
        return basketItems.any { it.meals_name == productName }
    }
}

