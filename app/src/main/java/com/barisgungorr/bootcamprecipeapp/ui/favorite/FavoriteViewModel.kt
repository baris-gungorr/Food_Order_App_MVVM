package com.barisgungorr.bootcamprecipeapp.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisgungorr.bootcamprecipeapp.data.repository.MealsRepository
import com.barisgungorr.bootcamprecipeapp.domain.FavoriteMeal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val mealsRepository: MealsRepository
) : ViewModel() {

    val favoriteList = MutableLiveData<List<FavoriteMeal>>()

    fun getFavorites() {
        viewModelScope.launch {
            favoriteList.value = mealsRepository.getFavorites()
        }
    }

    fun deleteFavorite(mealId: Int) {
        viewModelScope.launch {
            mealsRepository.deleteFavorite(mealId)
            getFavorites()
        }
    }

    fun searchFavorite(searchKeyword: String) {
        viewModelScope.launch {
            favoriteList.value = mealsRepository.searchFavorite(searchKeyword)
        }
    }
}
