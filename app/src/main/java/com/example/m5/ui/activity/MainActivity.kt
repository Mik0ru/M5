package com.example.m5.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.m5.data.CalculateResult
import com.example.m5.data.RetrofitInstance
import com.example.m5.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}