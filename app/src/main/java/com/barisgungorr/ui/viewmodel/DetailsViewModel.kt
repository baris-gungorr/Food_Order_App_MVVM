package com.barisgungorr.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.barisgungorr.data.repo.MealsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(var mrepo: MealsRepository) : ViewModel() {

      fun addMeals(yemek_adi: String,yemek_resim_adi: String,yemek_fiyat: Int,yemek_siparis_adet: Int,kullanici_adi: String) {
          CoroutineScope(Dispatchers.Main).launch {
              mrepo.addMeals(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
          }
      }
    }
