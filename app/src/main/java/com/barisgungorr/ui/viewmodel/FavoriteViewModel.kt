package com.barisgungorr.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barisgungorr.data.entity.Yemekler
import com.barisgungorr.data.repo.MealsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(var mRepo: MealsRepository) : ViewModel() {

    var mealList = MutableLiveData<List<Yemekler>>()




}
