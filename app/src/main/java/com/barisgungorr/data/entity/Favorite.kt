package com.barisgungorr.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "fav")
data class Favorite(@PrimaryKey(autoGenerate = true)
                    @ColumnInfo (name = "yemek_id") @NotNull var yemek_id:Int,
                    @ColumnInfo (name = "yemek_adi") @NotNull  val yemek_adi:String,
                    @ColumnInfo (name = "yemek_resim_adi") @NotNull  val yemek_resim_adi:String):Serializable {

}