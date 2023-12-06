package com.barisgungorr.bootcamprecipeapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.Basket
import com.barisgungorr.bootcamprecipeapp.data.repo.MealsRepository
import com.barisgungorr.bootcamprecipeapp.utils.constans.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(private val mealsRepository: MealsRepository) : ViewModel() {
    private var basketList: MutableLiveData<List<Basket>> = MutableLiveData()
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
        mealsName: String,
        mealsImageName: String,
        mealsPrice: Int,
        mealsOrderPiece: Int,
        userName: String
    ) {
        viewModelScope.launch {
            try {
                mealsRepository.addMeals(
                    mealsName,
                    mealsImageName,
                    mealsPrice,
                    mealsOrderPiece,
                    userName
                )

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getBasketMeals() {

        viewModelScope.launch {
            try {
                basketList.value = mealsRepository.getMeals(AppConstants.USERNAME)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun save(mealsId: Int, mealsName: String, mealsImageName: String) {
        viewModelScope.launch {
            mealsRepository.save(mealsId, mealsName, mealsImageName)
        }
    }

    fun isProductInBasket(productName: String): Boolean {
        val basketItems = basketList.value.orEmpty()
        return basketItems.any { it.name == productName }
    }
}



