package com.example.test04

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.test04.databinding.ActivityStep2Binding

class Step2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityStep2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // (좌) 추가 코드

        // 버튼 리스트 생성
        val whoBtnList = listOf(
            binding.babyBtn,
            binding.petBtn,
            binding.patientBtn,
            binding.kidBtn,
            binding.seniorBtn,
            binding.disabledBtn
        )

        // 선택 상태를 토글하는 함수
        fun toggleSelection(button: View) {
            val isSelected = button.isSelected
            button.isSelected = !isSelected
        }

        // 버튼들에 대한 클릭 리스너 설정
        whoBtnList.forEach { button ->
            button.setOnClickListener { toggleSelection(it) }
        }

        // 페이지 이동
        binding.nextBtn.setOnClickListener {
            val intent = Intent(this, Step3Activity::class.java)
            startActivity(intent)
            true
        }
        // (좌) 추가 코드 끝


    }
}