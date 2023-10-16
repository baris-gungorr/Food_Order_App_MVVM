package com.barisgungorr.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barisgungorr.data.entity.Meals
import com.barisgungorr.data.entity.Sepetler
import com.barisgungorr.data.entity.Yemekler
import com.barisgungorr.data.repo.MealsRepository
import com.bumptech.glide.Glide.init
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor ( var mRepo: MealsRepository) : ViewModel(){
    private val cacheList = mutableListOf<Yemekler>()
    var mealList = MutableLiveData<List<Yemekler>>()

    init {
        getMeals()
    }

fun getMeals() {
    CoroutineScope(Dispatchers.Main).launch {
        try {
            //mealList.value = mRepo.getMeals()
            val meals = mRepo.getMeals()
            mealList.value = meals
            cacheList.addAll(meals)

        }catch (e:Exception) {

        }
    }
    }
    fun search(searchKeyword:String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                if (searchKeyword.isEmpty()) {
                    mealList.value = cacheList
                } else {
                    mealList.value = cacheList.filter {
                        it.yemek_adi.lowercase().contains(searchKeyword.lowercase())
                    }
                }

            }catch (e:Exception) {
            }
        }
    }
}

