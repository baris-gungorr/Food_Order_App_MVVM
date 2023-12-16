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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val mealsRepository: MealsRepository
) : ViewModel() {

    private var basketList: MutableLiveData<List<BasketMealResponse>> = MutableLiveData()

    var uiModel = MutableStateFlow(DetailUiModel())

    val message = MutableSharedFlow<Int>()

    private val currentUIModel get() = uiModel.value

    val shouldNavigateToMainScreen = MutableSharedFlow<Unit>()

    init {
        getBasketMeals()
    }

    fun initMeal(meal: MealResponse) {
        uiModel.update {
            it.copy(
                meal = meal,
                price = meal.price * currentUIModel.piece
            )
        }
    }

    fun decreaseQuantity() {
        val meal = currentUIModel.meal ?: return
        val currentPiece = currentUIModel.piece
        if (currentPiece > 1) {
            val newPiece = currentPiece.dec()
            uiModel.update {
                it.copy(
                    piece = newPiece,
                    price = meal.price * newPiece
                )
            }
        }
    }

    fun increaseQuantity() {
        val currentPiece = currentUIModel.piece
        val meal = currentUIModel.meal ?: return
        val newPiece = currentPiece.inc()
        uiModel.update {
            it.copy(
                piece = newPiece,
                price = meal.price * newPiece
            )
        }
    }

    private fun addMeals(
        mealsName: String,
        mealsImageName: String,
        mealsPrice: Int,
        mealsOrderPiece: Int,
        username: String = AppConstants.USERNAME
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
            basketList.value = runCatching {
                mealsRepository.getMeals(AppConstants.USERNAME)
            }.getOrNull()
        }
    }

    fun save() {
        val meal = currentUIModel.meal ?: return
        viewModelScope.launch {
            mealsRepository.save(
                mealId = meal.id,
                mealName = meal.name,
                mealImage = meal.imageName
            )
        }
    }

    private fun isProductInBasket(productName: String): Boolean {
        val basketItems = basketList.value.orEmpty()
        return basketItems.any { it.name == productName }
    }

    fun addToCart() {
        val meal = currentUIModel.meal ?: return
        viewModelScope.launch {
            getBasketMeals()
            if (isProductInBasket(meal.name)) {
                sendMessage(R.string.detail_page_card_error)
            } else {
                addMeals(
                    meal.name,
                    meal.imageName,
                    meal.price,
                    uiModel.value.piece
                )
                sendMessage(R.string.detail_page_add_card)
            }
        }
    }


    private fun sendMessage(messageResId: Int) {
        viewModelScope.launch {
            this@DetailsViewModel.message.emit(messageResId)
        }
    }
}

data class DetailUiModel(
    val meal: MealResponse? = null,
    val piece: Int = 1,
    val price: Int = 0
)