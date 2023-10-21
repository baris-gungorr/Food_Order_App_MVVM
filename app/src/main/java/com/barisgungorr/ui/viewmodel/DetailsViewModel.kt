package com.barisgungorr.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisgungorr.data.entity.Meals
import com.barisgungorr.data.entity.Sepetler
import com.barisgungorr.data.repo.MealsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(var mrepo: MealsRepository) : ViewModel() {
    var basketList: MutableLiveData<List<Sepetler>> = MutableLiveData()

    init {
        getBasketMeals("BarisGungor")
    }
    fun addMeals(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            mrepo.addMeals(
                yemek_adi,
                yemek_resim_adi,
                yemek_fiyat,
                yemek_siparis_adet,
                kullanici_adi
            )
        }
    }

    fun getBasketMeals(kullanici_adi: String) {

        CoroutineScope(Dispatchers.Main).launch {
            try {
                basketList.value = mrepo.getBasketMeals(kullanici_adi)

            } catch (e: Exception) {
            }
        }
    }

    fun save(yemek_id:Int,yemek_adi: String, yemek_resim_adi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            mrepo.save(yemek_id,yemek_adi, yemek_resim_adi)
        }
    }

}
