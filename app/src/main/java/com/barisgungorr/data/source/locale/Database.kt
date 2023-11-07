package com.barisgungorr.data.source.locale

import androidx.room.Database
import androidx.room.RoomDatabase
import com.barisgungorr.data.entity.Favorite

@Database(entities = [Favorite::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun getFavoritesDao() : FavoriteDao
}