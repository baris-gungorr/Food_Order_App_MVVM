package com.barisgungorr.data.di

import com.barisgungorr.data.datasource.MealsDataSource
import com.barisgungorr.data.repo.MealsRepository
import com.barisgungorr.ui.retrofit.ApiUtils
import com.barisgungorr.ui.retrofit.HomeMealsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideMealsDataSource(mdao: HomeMealsDao): MealsDataSource {
        return MealsDataSource(mdao)
    }

    @Provides
    @Singleton
    fun provideMealsDao(): HomeMealsDao {
        return ApiUtils.getMealsDao()
    }


}