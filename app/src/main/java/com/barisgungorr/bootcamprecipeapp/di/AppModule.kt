package com.barisgungorr.bootcamprecipeapp.di

import android.content.Context
import androidx.room.Room
import com.barisgungorr.bootcamprecipeapp.data.datasource.MealsDataSource
import com.barisgungorr.bootcamprecipeapp.data.source.locale.Database
import com.barisgungorr.bootcamprecipeapp.data.source.locale.FavoriteDao
import com.barisgungorr.bootcamprecipeapp.data.source.remote.HomeMealsApi
import com.barisgungorr.bootcamprecipeapp.ui.retrofit.ApiUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideMealsDataSource(mdao: HomeMealsApi, fdao: FavoriteDao): MealsDataSource {
        return MealsDataSource(mdao,fdao)
    }

    @Provides
    @Singleton
    fun provideMealsDao(): HomeMealsApi {
        return ApiUtils.getMealsDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(@ApplicationContext context:Context): FavoriteDao {
        val vt = Room.databaseBuilder(context, Database::class.java,"fav.sqlite").createFromAsset("fav.sqlite").build()
        return vt.getFavoritesDao()
    }
}