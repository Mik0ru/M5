package com.example.m5.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HistoryDao {

    @Insert
    suspend fun insertHistory(entity: HistoryEntity)

    @Delete
    suspend fun deleteHistory(entity: HistoryEntity)

    @Update
    suspend fun updateHistory(entity: HistoryEntity)

    @Query("SELECT * FROM history")
    suspend fun getAll(): List<HistoryEntity>
}