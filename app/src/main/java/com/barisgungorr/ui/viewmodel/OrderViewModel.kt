package com.barisgungorr.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.barisgungorr.data.repo.MealsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OrderViewModel @Inject constructor(var mrepo: MealsRepository): ViewModel() {

    fun addMeals(mealsName:String,meals_image_name:String,meals_price:Int,meals_order_piece:Int,user_name:String) {
        CoroutineScope(Dispatchers.Main).launch {
            mrepo.addMeals(mealsName, meals_image_name, meals_price, meals_order_piece, user_name)
        }
    }

}