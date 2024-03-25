package com.example.test04

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.test04.databinding.ActivityStep2Binding

class Step2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityStep2Binding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}