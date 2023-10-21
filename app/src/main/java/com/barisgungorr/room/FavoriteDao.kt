package com.barisgungorr.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.barisgungorr.data.entity.Favorite

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM fav")
    suspend fun getFavorites() : List<Favorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(favorite: Favorite)

    @Delete
    suspend fun deleteF(favorite: Favorite)

    @Query("SELECT * FROM fav WHERE yemek_adi like '%' || :searchKeyword || '%' ")
    suspend fun searchF(searchKeyword:String) : List<Favorite>

}