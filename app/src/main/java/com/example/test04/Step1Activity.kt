package com.example.test04

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.Pair
import com.example.test04.databinding.ActivityStep1Binding
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class Step1Activity : AppCompatActivity() {

    private lateinit var btShowDateRangePicker: Button
    private lateinit var selectDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityStep1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbarStep1
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val dummyText = binding.dummyText

        btShowDateRangePicker = binding.btShowPicker
        selectDate = binding.selectDate

        btShowDateRangePicker.setOnClickListener {
            val picker = MaterialDatePicker.Builder.dateRangePicker()
                .setTheme(R.style.ThemeMaterialCaledar)
                .setTitleText("Select Date Range Tutorial")
                .setSelection(Pair(null, null))
                .build()

            picker.show(this.supportFragmentManager, "TAG")

            picker.addOnPositiveButtonClickListener {
                selectDate.setText(convertTimeToDate(it.first) + "-" + convertTimeToDate(it.second))
            }

            picker.addOnNegativeButtonClickListener{
                picker.dismiss()
            }
        }
        if (!Places.isInitialized()){
            Places.initialize(applicationContext,"AIzaSyDiKfKDPaOVPmMixJcHTvtFm7ce_GhhFoQ")
        }

        val autocompleteSupportFragment = (supportFragmentManager.findFragmentById(R.id.searchFragment) as AutocompleteSupportFragment).setPlaceFields(
            listOf(Place.Field.LAT_LNG, Place.Field.NAME)
        )

        autocompleteSupportFragment.setOnPlaceSelectedListener(object: PlaceSelectionListener{
            override fun onError(p0: Status) {
                Log.e("error",p0.statusMessage.toString())
            }

            override fun onPlaceSelected(p0: Place) {
                if (p0.latLng!=null){
                    val latLng = p0.latLng
                    dummyText.text = p0.name!!.toString()
                    Toast.makeText(this@Step1Activity,latLng.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })
        /* 원래 코드
        binding.complete.setOnClickListener {
            val intent = intent
            intent.putExtra("result_trip_name", binding.dummyText.text.toString())
            intent.putExtra("result_trip_period", binding.selectDate.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
            true
        }
        */

        // (좌) 추가 코드
        binding.step1Next.setOnClickListener {
            val intent = Intent(this, Step2Activity::class.java)
            startActivity(intent)
            true
        }


    }
    private fun convertTimeToDate(time: Long): String {
        val utc = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        utc.timeInMillis = time
        val format = SimpleDateFormat("dd/MM/yyy", Locale.getDefault())
        return format.format(utc.time)
    }


}