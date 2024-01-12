package com.barisgungorr.bootcamprecipeapp.data.source.locale

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.barisgungorr.bootcamprecipeapp.data.entity.FavoriteEntity

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM fav")
    suspend fun getFavorites(): List<FavoriteEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(favorite: FavoriteEntity)
    @Delete
    suspend fun deleteFavorite(favorite: FavoriteEntity)

    @Query("SELECT * FROM fav WHERE yemek_adi like '%' || :searchKeyword || '%' ")
    suspend fun searchFavorite(searchKeyword: String): List<FavoriteEntity>

}