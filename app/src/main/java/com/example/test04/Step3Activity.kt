package com.example.test04

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.test04.databinding.ActivityStep3Binding

class Step3Activity : AppCompatActivity() {

    private val step3selectedButtons = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityStep3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbarStep3
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
            binding.alcoholBtn,
            binding.fitnessBtn,
            binding.businessBtn
        )

        // 선택 상태를 토글하는 함수
        fun toggleSelection(button: View) {
            val buttonResourceName = resources.getResourceEntryName(button.id)
            if (step3selectedButtons.contains(buttonResourceName)) {
                step3selectedButtons.remove(buttonResourceName)
                button.isSelected = false
            } else {
                step3selectedButtons.add(buttonResourceName)
                button.isSelected = true
            }
        }

        // 버튼들에 대한 클릭 리스너 설정
        tripTypeBtnList.forEach { button ->
            button.setOnClickListener { toggleSelection(it) }
        }

        //skip 버튼
        binding.skipBtnStep3.setOnClickListener {
            if (step3selectedButtons.isNotEmpty()) {
                // step3selectedButtons가 비어있지 않을 때만 확인 팝업을 표시합니다.
                val builder = AlertDialog.Builder(this)
                builder.setMessage("정말로 스킵하시겠습니까?")
                    .setCancelable(false)
                    .setPositiveButton("예") { dialog, _ ->
                        // "예" 버튼을 클릭한 경우 CheckListActivity로 이동합니다.
                        val intent = Intent(this, CheckListActivity::class.java)
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
                // step3selectedButtons가 비어있는 경우에는 바로 CheckListActivity로 이동합니다.
                val intent = Intent(this, CheckListActivity::class.java)
                startActivity(intent)
            }
        }


        //next 버튼
        binding.nextBtnStep3.setOnClickListener {
            val intent = Intent(this, CheckListActivity::class.java)
            //step2에서 선택한 버튼과 step3에서 선택한 버튼 합치는 과정
            intent.getStringArrayListExtra("step2selectedButtons")?.let { step3selectedButtons.addAll(it) }
            Log.d("step3SelectedButtons",step3selectedButtons.toString() )
            intent.putStringArrayListExtra("step3selectedButtons",step3selectedButtons)
            startActivity(intent)
            true
        }


    }
}