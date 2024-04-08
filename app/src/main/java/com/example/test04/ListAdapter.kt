package com.example.test04

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test04.databinding.ItemListBinding

class ListAdapter(private val selectedButtons: List<String>): RecyclerView.Adapter<ListAdapter.MyView>() {
    private val buttonItemsMap = mapOf(

        "babyBtn" to listOf("기저귀", "턱받이", "아기 옷","아기 수저세트", "아기 물병", "아기용 베개","장난감","힙시트",
        "휴대용 유모차","분유","아기 담요","아기 간식","아기 치약","아기 바디워시","아기 샴푸","아기 로션","아기 크림","아기 선크림",
        "체온계"),

        "petBtn" to listOf("켄넬","배변패드","강아지 수건","강아지 샴푸","강아지 빗","배변봉투","리드줄","강아지 간식","강아지 건강 수첩",
        "강아지 사료","애착 인형","강아지 옷","강아지 약"),

        "patientBtn" to listOf("개인 의약품","마스크","손 소독제")


    )


    inner class MyView(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.layout01.setOnClickListener {
                if(binding.layoutDetail01.visibility == View.VISIBLE) {
                    binding.layoutDetail01.visibility = View.GONE
                    binding.layoutBtn01.animate().apply {
                        duration = 200
                        rotation(0f)
                    }
                } else {
                    binding.layoutDetail01.visibility = View.VISIBLE
                    binding.layoutBtn01.animate().apply {
                        duration = 200
                        rotation(180f)
                    }
                }
            }
        }

        fun bind(buttonName: String) {
            binding.textView01.text = buttonName
            val items = buttonItemsMap[buttonName]
            if (items != null) {
                items.forEach {
                    val view = TextView(binding.root.context).apply {
                        text = "· $it"
                        textSize = 20f
                        setPadding(10, 10, 5, 10)
                    }
                    binding.layoutDetail01.addView(view)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyView(view)
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        val buttonName = selectedButtons.getOrNull(position)
        if (buttonName != null) {
            holder.bind(buttonName)
        }
    }

    override fun getItemCount(): Int {
        return selectedButtons.size
    }
}
