package com.barisgungorr.data.di

import android.content.Context
import androidx.room.Room
import com.barisgungorr.data.datasource.MealsDataSource
import com.barisgungorr.data.source.locale.Database
import com.barisgungorr.data.source.locale.FavoriteDao
import com.barisgungorr.data.source.remote.HomeMealsApi
import com.barisgungorr.ui.retrofit.ApiUtils
import com.barisgungorr.utils.constans.Constans
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
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constans.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMealsApi(retrofit: Retrofit): HomeMealsApi {
        return retrofit.create(HomeMealsApi::class.java)
    }



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