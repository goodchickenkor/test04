package com.example.test04

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.test04.databinding.TripItemBinding

class MyViewHolder(val binding: TripItemBinding): RecyclerView.ViewHolder(binding.root) {

    val TAG: String = "로그"

    //여기 수정해야 될수도..
    //val binding = TripItemBinding.inflate(LayoutInflater.from(itemView.context), null, false)

    private var tripNameTextView =  binding.tripName
    private var tripPeriodTextView =  binding.tripPeriod
    //여기까지

    //기본 생성자
    init {
        Log.d(TAG, "MyViewHolder - init() called")
    }

    // 데이터와 뷰를 묶는다.
    fun bind(tripmodel: TripModel){
        Log.d(TAG,"MyViewHolder - bind() called")
        tripNameTextView.text = tripmodel.trip_name.toString()
        tripPeriodTextView.text = tripmodel.trip_period.toString()
    }

}