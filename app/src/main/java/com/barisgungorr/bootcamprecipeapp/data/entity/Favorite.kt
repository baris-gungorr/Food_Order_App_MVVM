package com.barisgungorr.bootcamprecipeapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "fav")
data class Favorite(@PrimaryKey(autoGenerate = true)
                    @ColumnInfo (name = "yemek_id") var mealsId:Int,
                    @ColumnInfo (name = "yemek_adi")  val mealsName:String,
                    @ColumnInfo (name = "yemek_resim_adi")  val mealsImageName:String):Serializable

