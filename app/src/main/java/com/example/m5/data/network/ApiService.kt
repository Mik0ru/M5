package com.example.m5.data.network

import com.example.m5.data.network.model.LoveModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("getPercentage")
    fun getPercentage(
        @Query("fname") firstName: String,
        @Query("sname") secondName:String,
        @Header("x-rapidapi-key") key :String,
        @Header("x-rapidapi-host") host: String,
    ): Call<LoveModel>
}