package com.example.test04

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test04.databinding.ActivityCheckListBinding

class CheckListActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCheckListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //날씨 기능 추가


        //선택한 모든 버튼의 이름들
        val selectedButtons = intent.getStringArrayListExtra("step3selectedButtons") ?: arrayListOf()

        Log.d("SelectedButtons", selectedButtons.toString())


        binding.recycler01.apply {
            adapter = ListAdapter(selectedButtons)
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        binding.makeTripList.setOnClickListener {
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}