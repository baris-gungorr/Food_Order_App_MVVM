package com.barisgungorr.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barisgungorr.data.entity.Meals
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
    var mealList = MutableLiveData<List<Yemekler>>()



    init {
        getMeals()

    }

fun getMeals() {
    CoroutineScope(Dispatchers.Main).launch {
        mealList.value = mRepo.getMeals()

    }
}
}
