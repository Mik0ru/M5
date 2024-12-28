package com.example.m5

import android.app.Application
import androidx.room.Room
import com.example.m5.data.local.HistoryDao
import com.example.m5.data.local.HistoryDatabase
import com.example.m5.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://love-calculator.p.rapidapi.com/")
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application, ): HistoryDatabase{
      return Room.databaseBuilder(application, HistoryDatabase::class.java, "historyDatabase").build()
    }

    @Provides
    @Singleton
    fun provideHistoryDao(
        historyDatabase: HistoryDatabase): HistoryDao {
        return historyDatabase.historyDao()
    }







}