package com.example.test04

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.test04.databinding.ActivityStep4Binding

class Step4Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityStep4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 버튼 리스트 생성
        val tripTypeBtnList = listOf(
            binding.campingBtn,
            binding.swimmingBtn,
            binding.glampingBtn,
            binding.mountainBtn,
            binding.skiBtn,
            binding.fishingBtn,
            binding.golfBtn,
            binding.concertBtn,
            binding.cameraBtn,
            binding.trekkingBtn,
            binding.waterfallBtn,
            binding.spaBtn,
            binding.alcoholBtn,
            binding.fitnessBtn,
            binding.businessBtn
        )

        // 선택 상태를 토글하는 함수
        fun toggleSelection(button: View) {
            val isSelected = button.isSelected
            button.isSelected = !isSelected
        }

        // 버튼들에 대한 클릭 리스너 설정
        tripTypeBtnList.forEach { button ->
            button.setOnClickListener { toggleSelection(it) }
        }



    }
}