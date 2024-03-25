package com.example.test04

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test04.databinding.TripItemBinding

class MyRecyclerAdapter: RecyclerView.Adapter<MyViewHolder>() {

    val TAG: String = "로그"

    private var modelList = ArrayList<TripModel>()

    // 뷰홀더가 생성 됐을때
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // 연결할 레이아웃 설정
        val binding = TripItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    //목록의 아이템 수
    override fun getItemCount(): Int {
        return this.modelList.size
    }
    //뷰와 뷰홀더가 묶였을때
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d(TAG, "MyRecyclerAdapter - onBindViewHolder() called")
        val binding = holder.binding
        binding.tripName.text = modelList[position].trip_name
        binding.tripPeriod.text = modelList[position].trip_period
        holder.bind(this.modelList[position])
    }

    //외부에서 데이터 넘기기
    fun submitList(modelList: ArrayList<TripModel>){
        this.modelList = modelList
    }
}