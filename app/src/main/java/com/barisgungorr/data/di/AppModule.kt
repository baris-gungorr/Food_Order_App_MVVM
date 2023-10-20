package com.barisgungorr.data.di

import android.content.Context
import androidx.room.Room
import com.barisgungorr.data.datasource.MealsDataSource
import com.barisgungorr.data.repo.MealsRepository
import com.barisgungorr.room.Database
import com.barisgungorr.room.FavoriteDao
import com.barisgungorr.ui.retrofit.ApiUtils
import com.barisgungorr.ui.retrofit.HomeMealsDao
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
    fun provideMealsDataSource(mdao: HomeMealsDao,fdao:FavoriteDao): MealsDataSource {
        return MealsDataSource(mdao,fdao)
    }

    @Provides
    @Singleton
    fun provideMealsDao(): HomeMealsDao {
        return ApiUtils.getMealsDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(@ApplicationContext context:Context): FavoriteDao {
        val vt = Room.databaseBuilder(context,Database::class.java,"fav.sqlite").createFromAsset("fav.sqlite").build()
        return vt.getFavoritesDao()
    }


}