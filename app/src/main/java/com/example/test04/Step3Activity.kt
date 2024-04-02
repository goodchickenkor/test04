package com.example.test04

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.test04.databinding.ActivityStep3Binding

class Step3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityStep3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 버튼 리스트 생성
        val transportBtnList = listOf(
            binding.busBtn,
            binding.bicycleBtn,
            binding.shipBtn,
            binding.trainBtn,
            binding.airplaneBtn,
            binding.carBtn,
            binding.motorcycleBtn,

        )

        // 선택 상태를 토글하는 함수
        fun toggleSelection(button: View) {
            val isSelected = button.isSelected
            button.isSelected = !isSelected
        }

        // 버튼들에 대한 클릭 리스너 설정
        transportBtnList.forEach { button ->
            button.setOnClickListener { toggleSelection(it) }
        }

        // 페이지 이동
        binding.nextBtn.setOnClickListener {
            val intent = Intent(this, Step4Activity::class.java)
            startActivity(intent)
            true
        }

    }
}