package com.example.test04

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test04.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val TAG: String = "로그"

    //데이터 담을 리스트
    var tripModelList = ArrayList<TripModel>()

    private lateinit var myRecyclerAdapter: MyRecyclerAdapter

    //뷰가 화면에 그려질 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    //    for (i in 1..10){
    //            val tripmodel = TripModel(trip_name = "서울", trip_period = "2024")
    //             this.tripModelList.add(tripmodel)
    //    }


        //어댑터 인스턴스 생성
        myRecyclerAdapter = MyRecyclerAdapter()
        myRecyclerAdapter.submitList(this.tripModelList)


        binding.tripList.apply {
            //리사이클러뷰 설정
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL,
            false)
            adapter = myRecyclerAdapter
        }

        // 플로팅 버튼 누른 후 화면 전환
        val requestLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            //결과 처리 Callback 객체 등록
            val result_trip_name = it.data?.getStringExtra("result_trip_name")
            val result_trip_period = it.data?.getStringExtra("result_trip_period")
            val tripmodel = TripModel(trip_name = result_trip_name, trip_period = result_trip_period)
            this.tripModelList.add(tripmodel)
            myRecyclerAdapter.notifyDataSetChanged()
            //tripModelList += TripModel(trip_name = result_trip_name, trip_period = result_trip_period)
        }

        binding.addTrip.setOnClickListener {
            val intent = Intent(this,Step1Activity::class.java)
            requestLauncher.launch(intent)
        }


    }
}