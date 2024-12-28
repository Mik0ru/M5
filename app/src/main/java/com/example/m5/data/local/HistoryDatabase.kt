package com.example.m5.data.local

import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntity::class], version = 3)
abstract class HistoryDatabase: RoomDatabase() {

    abstract fun historyDao() : HistoryDao



}