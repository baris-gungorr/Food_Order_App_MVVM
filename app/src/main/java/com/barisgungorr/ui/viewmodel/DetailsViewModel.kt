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
class DetailsViewModel @Inject constructor(private val mrepo: MealsRepository) : ViewModel() {
    private var basketList: MutableLiveData<List<Sepetler>> = MutableLiveData()

    init {
        getBasketMeals()
    }
    fun addMeals(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val isProductInBasket = isProductInBasket(yemek_adi)
                if (isProductInBasket) {

                } else {
                    mrepo.addMeals(
                        yemek_adi,
                        yemek_resim_adi,
                        yemek_fiyat,
                        yemek_siparis_adet,
                        kullanici_adi
                    )
                }
            } catch (e: Exception) {

            }
        }
    }

    private fun getBasketMeals() {

        CoroutineScope(Dispatchers.Main).launch {
            try {
                basketList.value = mrepo.getBasketMeals("BarisGungor")

            } catch (e: Exception) {

            }
        }
    }

    fun save(yemek_id:Int,yemek_adi: String, yemek_resim_adi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            mrepo.save(yemek_id,yemek_adi, yemek_resim_adi)
        }
    }
    fun isProductInBasket(productName: String): Boolean {
        val basketItems = basketList.value ?: emptyList()
        return basketItems.any { it.meals_name == productName }
    }
}

