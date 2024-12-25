package com.example.m5

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.m5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CounterView {

    private var binding: ActivityMainBinding?= null
    private val presenter = CounterPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        presenter.attachView(this)

        binding?.apply {
            btnIncrement.setOnClickListener {
                presenter.onIncrementClicked()
            }
            btnDecrement.setOnClickListener {
                presenter.onDecrementClicked()
            }
        }
    }

    override fun updateCounter(count: Int, color: String) {
        binding?.tvResult?.text = count.toString()
        binding?.tvResult?.setTextColor(android.graphics.Color.parseColor(color))
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}