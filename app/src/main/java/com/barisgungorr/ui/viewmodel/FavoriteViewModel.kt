package com.barisgungorr.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barisgungorr.data.entity.Favorite
import com.barisgungorr.data.entity.Yemekler
import com.barisgungorr.data.repo.MealsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(var mRepo: MealsRepository) : ViewModel() {

    var favoriteList = MutableLiveData<List<Favorite>>()

    fun getFavorites() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                favoriteList.value = mRepo.getFavorites()
            } catch (e: Exception) {
            }
        }
    }

    fun deleteF(yemek_id: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            mRepo.deleteF(yemek_id)
            getFavorites()
        }
    }

    fun searchF(searchKeyword: String) {
        CoroutineScope(Dispatchers.Main).launch {
            favoriteList.value = mRepo.searchF(searchKeyword)
        }
    }
}
