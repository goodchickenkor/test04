package com.example.test04

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.BaseExpandableListAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test04.databinding.ActivityCheckListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CheckListActivity: AppCompatActivity() {
    private lateinit var binding: ActivityCheckListBinding
    private lateinit var expandableAdapter: BaseExpandableListAdapter
    private lateinit var categoryNameList : MutableList<String>
    private lateinit var ItemsList : MutableList<MutableList<Item>>
    private val dummyList : List<String> = listOf(DF_BASIC, DF_HYGIENE, WITH_BABYORKID, DO_CAMPING)

    //편집하는 액티비티로 가는 registerForActivityResult 구현

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }


    private fun initExpandableListView(){
        expandableAdapter = ExpandableListAdapter(this, categoryNameList, ItemsList)
        binding.expandableListView.apply {
            setAdapter(expandableAdapter)
            expandableAdapter.notifyDataSetChanged()


        }
    }


    //intent로 이전 액티비티들에서 선택된 category들의 목록을 받아오면 그에 따른 리스트를 만듬
    private fun init(){
        //val categoryCodeArray = intent.getFloatArrayExtra("이전 액티비티에서 넘겨준 실수(카테고리 constant)배열") //인텐트로 실수 배열을 넘겨받았을 시
        CoroutineScope(Dispatchers.IO).launch {
            val categoryCodeArray = dummyList // 또는 intent에서 받아온 데이터 사용
            val tempList = mutableListOf<MutableList<Item>>()

            categoryCodeArray.forEach { category ->
                val items = ItemDatabase.getInstance(applicationContext)?.itemDao()?.getAllOfThisCategory(category)
                items?.forEach { it->
                    Log.i("itemInit",it.name ?: "default")
                }
                if (items != null) {
                    tempList.add(items)
                    Log.i("itemInit", (items[0].category ?: "default"))
                }
            }

            withContext(Dispatchers.Main) {
                ItemsList = tempList
                categoryNameList = ItemsList.mapNotNull { it.firstOrNull()?.category }.toMutableList()
                initExpandableListView()
            }
        }
    }
}