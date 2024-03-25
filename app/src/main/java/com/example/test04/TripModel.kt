package com.example.test04

import android.util.Log

class TripModel(var trip_name: String? = null, var trip_period: String? = null) {
    val TAG: String = "로그"

    //기본 생성자
    init {
        Log.d(TAG, "TripModel - init() called")
    }
}