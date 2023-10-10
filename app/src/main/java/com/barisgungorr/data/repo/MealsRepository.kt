package com.barisgungorr.data.repo

import androidx.lifecycle.MutableLiveData
import com.barisgungorr.data.datasource.MealsDataSource
import com.barisgungorr.data.entity.Meals
import com.barisgungorr.data.entity.Yemekler
import com.barisgungorr.ui.retrofit.HomeMealsDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MealsRepository @Inject constructor(var mDao: MealsDataSource) {

    suspend fun getMeals(): List<Yemekler> = mDao.getMeals()



    }
