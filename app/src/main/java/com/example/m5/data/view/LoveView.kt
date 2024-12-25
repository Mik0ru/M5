package com.example.m5.data.view

import com.example.m5.data.model.LoveModel

interface LoveView {
    fun showLoading(isLoading: Boolean)
    fun showResult(loveModel: LoveModel)
    fun showToast(message: String)
}