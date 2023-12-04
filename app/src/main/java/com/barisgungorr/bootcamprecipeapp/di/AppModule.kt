package com.barisgungorr.bootcamprecipeapp.di

import android.content.Context
import androidx.room.Room
import com.barisgungorr.bootcamprecipeapp.data.datasource.MealsDataSource
import com.barisgungorr.bootcamprecipeapp.data.source.locale.Database
import com.barisgungorr.bootcamprecipeapp.data.source.locale.FavoriteDao
import com.barisgungorr.bootcamprecipeapp.data.source.remote.HomeMealsApi
import com.barisgungorr.bootcamprecipeapp.data.retrofit.ApiUtils
import com.barisgungorr.bootcamprecipeapp.utils.constans.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideMealsDataSource(mealsDatasource: HomeMealsApi, favoriteDao: FavoriteDao): MealsDataSource {
        return MealsDataSource(mealsDatasource,favoriteDao)
    }

    @Provides
    @Singleton
    fun provideMealsDao(): HomeMealsApi {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(HomeMealsApi::class.java)

    }

    @Provides
    @Singleton
    fun provideFavoriteDao(@ApplicationContext context:Context): FavoriteDao {
        val vt = Room.databaseBuilder(context, Database::class.java,"fav.sqlite").createFromAsset("fav.sqlite").build()
        return vt.getFavoritesDao()
    }
}