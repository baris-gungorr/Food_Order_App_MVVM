package com.barisgungorr.bootcamprecipeapp.data.source.locale

import androidx.room.Database
import androidx.room.RoomDatabase
import com.barisgungorr.bootcamprecipeapp.data.entity.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun getFavoritesDao() : FavoriteDao
}