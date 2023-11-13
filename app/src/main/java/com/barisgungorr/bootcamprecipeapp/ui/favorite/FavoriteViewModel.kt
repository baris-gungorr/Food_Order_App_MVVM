package com.barisgungorr.bootcamprecipeapp.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisgungorr.bootcamprecipeapp.data.entity.Favorite
import com.barisgungorr.bootcamprecipeapp.data.repo.MealsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val mRepo: MealsRepository) : ViewModel() {

    val favoriteList = MutableLiveData<List<Favorite>>()

    fun getFavorites() {
        viewModelScope.launch {
            favoriteList.value = mRepo.getFavorites()
        }
    }

    fun deleteF(yemekId: Int) {
        viewModelScope.launch {
            mRepo.deleteF(yemekId)
            getFavorites()
        }
    }

    fun searchF(searchKeyword: String) {
        viewModelScope.launch {
            favoriteList.value = mRepo.searchF(searchKeyword)
        }
    }
}
