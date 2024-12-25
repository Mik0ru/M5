package com.example.m5.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoveModel(
    @SerializedName("fname")
    val firstName:String,
    @SerializedName("sname")
    val secondName:String,
    @SerializedName("percentage")
    val percentage: String,
    @SerializedName("result")
    val result: String,
) : Serializable
