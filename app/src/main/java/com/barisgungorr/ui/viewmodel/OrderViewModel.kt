package com.barisgungorr.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barisgungorr.data.entity.Sepetler
import com.barisgungorr.data.repo.MealsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OrderViewModel @Inject constructor(var mrepo: MealsRepository): ViewModel() {
    var basketList : MutableLiveData<List<Sepetler>> = MutableLiveData()

    init {
       getBasketMeals("BarisGungor")
    }

    fun getBasketMeals(kullanici_adi:String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                basketList.value = mrepo.getBasketMeals(kullanici_adi)
            }catch (e:Exception) {

            }
        }
    }
}
