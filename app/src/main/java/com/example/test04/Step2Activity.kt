package com.example.test04

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.test04.databinding.ActivityStep2Binding

class Step2Activity : AppCompatActivity() {
    private val step2selectedButtons = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityStep2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbarStep2
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
            val buttonResourceName = resources.getResourceEntryName(button.id)
            if (step2selectedButtons.contains(buttonResourceName)) {
                step2selectedButtons.remove(buttonResourceName)
                button.isSelected = false
            } else {
                step2selectedButtons.add(buttonResourceName)
                button.isSelected = true
            }
        }

        // 버튼들에 대한 클릭 리스너 설정
        whoBtnList.forEach { button ->
            button.setOnClickListener { toggleSelection(it) }
        }

        //skip 버튼
        binding.skipBtnStep2.setOnClickListener {
            if (step2selectedButtons.isNotEmpty()) {
                // step2selectedButtons가 비어있지 않을 때만 확인 팝업을 표시합니다.
                val builder = AlertDialog.Builder(this)
                builder.setMessage("정말로 스킵하시겠습니까?")
                    .setCancelable(false)
                    .setPositiveButton("예") { dialog, _ ->
                        // "예" 버튼을 클릭한 경우 Step3Activity로 이동합니다.
                        val intent = Intent(this, Step3Activity::class.java)
                        startActivity(intent)
                        dialog.dismiss()
                    }
                    .setNegativeButton("아니오") { dialog, _ ->
                        // "아니오" 버튼을 클릭한 경우 팝업을 닫습니다.
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()
            } else {
                // step2selectedButtons가 비어있는 경우에는 바로 Step3Activity로 이동합니다.
                val intent = Intent(this, Step3Activity::class.java)
                startActivity(intent)
            }
        }


        // 페이지 이동
        binding.nextBtnStep2.setOnClickListener {
            var intent = Intent(this, Step3Activity::class.java)
            Log.d("step2selectedButtons", step2selectedButtons.toString())
            intent.putStringArrayListExtra("step2selectedButtons",step2selectedButtons)
            startActivity(intent)
            true
        }


    }

}