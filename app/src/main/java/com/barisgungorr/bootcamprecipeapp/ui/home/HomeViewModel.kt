package com.barisgungorr.bootcamprecipeapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisgungorr.bootcamprecipeapp.data.entity.Meal
import com.barisgungorr.bootcamprecipeapp.data.repo.MealsRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val mRepo: MealsRepository) : ViewModel() {
    private val cacheList = mutableListOf<Meal>()
    var mealList = MutableLiveData<List<Meal>>()

    init {
        getMeals()
    }

    private fun getMeals() {
        viewModelScope.launch {
            try {
                val meals = mRepo.getMeals()
                mealList.value = meals
                cacheList.addAll(meals)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun search(searchKeyword: String) {
        viewModelScope.launch {
            if (searchKeyword.isEmpty()) {
                mealList.value = cacheList
            } else {
                mealList.value = cacheList.filter {
                    it.name.lowercase().contains(searchKeyword.lowercase())
                }
            }
        }
    }
    fun signOut() {
        Firebase.auth.signOut()
    }
}

