package com.example.m5

interface CounterView {
    fun updateCounter(count: Int, color: String)
    fun showToast(message: String)
}