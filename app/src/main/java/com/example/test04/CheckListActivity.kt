package com.example.test04

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.test04.databinding.ActivityCheckListBinding

class CheckListActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCheckListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}