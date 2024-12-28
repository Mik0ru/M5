package com.example.m5.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "history")
data class HistoryEntity(
    @ColumnInfo("first_name")
    val firstName:String,
    @ColumnInfo("second_nane")
    val secondName:String,
    val percentage: String,
    val result: String,
){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}