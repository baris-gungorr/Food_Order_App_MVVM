package com.barisgungorr.bootcamprecipeapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.data.datasource.MealsRepository
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.BasketMealResponse
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.MealResponse
import com.barisgungorr.bootcamprecipeapp.utils.constans.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(private val mealsRepository: MealsRepository) :
    ViewModel() {
    private var basketList: MutableLiveData<List<BasketMealResponse>> = MutableLiveData()
    var piece = MutableLiveData<Int>()
    val message = MutableSharedFlow<Int>()
    val shouldNavigateToMainScreen = MutableSharedFlow<Unit>()


    init {
        getBasketMeals()
        piece.value = 1

    }

    fun buttonDecrease() {
        val currentPiece = piece.value ?: 1
        if (currentPiece > 1) {
            piece.value = currentPiece - 1
        }
    }

    fun buttonQuantity() {
        val currentPiece = piece.value ?: 1
        piece.value = currentPiece + 1
    }

    private fun addMeals(
        mealsName: String,
        mealsImageName: String,
        mealsPrice: Int,
        mealsOrderPiece: Int,
        username: String
    ) {
        viewModelScope.launch {
            try {
                mealsRepository.addMeals(
                    mealsName,
                    mealsImageName,
                    mealsPrice,
                    mealsOrderPiece,
                    username
                )

                getBasketMeals()

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

    private fun isProductInBasket(productName: String): Boolean {
        val basketItems = basketList.value.orEmpty()
        return basketItems.any { it.name == productName }
    }

    fun handleButtonClick(meals: MealResponse) {
        viewModelScope.launch {
            getBasketMeals()

            val isAlreadyInCart = isProductInBasket(meals.name)

            when {
                isAlreadyInCart -> sendMessage(R.string.detail_page_card_error)
                else -> {
                    piece.value?.let {
                        addMeals(
                            meals.name,
                            meals.imageName,
                            meals.price,
                            it,
                            AppConstants.USERNAME)

                    }
                    sendMessage(R.string.detail_page_add_card)
                }
            }
        }
    }


    private fun sendMessage(messageResId: Int) {
        viewModelScope.launch {

            this@DetailsViewModel.message.emit(messageResId)
        }
    }
}



