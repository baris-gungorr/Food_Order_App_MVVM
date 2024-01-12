package com.barisgungorr.bootcamprecipeapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.barisgungorr.bootcamprecipeapp.domain.FavoriteMeal
import com.barisgungorr.bootcamprecipeapp.utils.constans.AppConstants
import java.io.Serializable

@Entity(tableName = "fav")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "yemek_id") var mealsId: Int,
    @ColumnInfo(name = "yemek_adi") val mealsName: String,
    @ColumnInfo(name = "yemek_resim_adi") val mealsImageName: String
) : Serializable

fun FavoriteEntity.toMeal(): FavoriteMeal {
    return FavoriteMeal(
        id = mealsId,
        name = mealsName,
        imageUrl = "${AppConstants.BASE_IMAGE_URL}${mealsImageName}"
    )
}