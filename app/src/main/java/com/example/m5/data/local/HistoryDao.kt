package com.example.m5.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HistoryDao {

    @Insert
    fun insertHistory(entity: HistoryEntity)

    @Delete
    fun deleteHistory(entity: HistoryEntity)

    @Update
    fun updateHistory(entity: HistoryEntity)

    @Query("SELECT * FROM history")
    fun getAll(): List<HistoryEntity>
}