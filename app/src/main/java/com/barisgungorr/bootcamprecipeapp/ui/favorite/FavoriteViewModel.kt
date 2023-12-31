package com.barisgungorr.bootcamprecipeapp.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisgungorr.bootcamprecipeapp.data.datasource.MealsRepository
import com.barisgungorr.bootcamprecipeapp.data.entity.Favorite
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val mealsRepository: MealsRepository) :
    ViewModel() {

    val favoriteList = MutableLiveData<List<Favorite>>()

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
