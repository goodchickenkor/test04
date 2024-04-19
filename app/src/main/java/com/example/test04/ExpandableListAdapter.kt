package com.example.test04


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.ListAdapter
import com.example.test04.databinding.CategotyInListBinding
import com.example.test04.databinding.ItemInListBinding

class ExpandableListAdapter(
    private val context : Context,
    private val categoryList : MutableList<String>,
    private val ItemsList : MutableList<MutableList<Item>>
) : BaseExpandableListAdapter(){
    override fun getGroupCount(): Int {
        return categoryList.size
    }

    override fun getChildrenCount(categoryIndex: Int): Int {
        return ItemsList[categoryIndex].size
    }

    override fun getGroup(categoryIndex: Int): Any {
        return categoryList[categoryIndex]
    }

    override fun getChild(categoryIndex: Int, itemIndex: Int): Any {
        return ItemsList[categoryIndex][itemIndex]
    }

    override fun getGroupId(categoryIndex: Int): Long {
        return categoryIndex.toLong()
    }

    override fun getChildId(categoryIndex: Int, itemIndex: Int): Long {
        return itemIndex.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(categoryIndex: Int, isExpanded: Boolean, convertView: View?, parentLayout: ViewGroup?): View {
        val binding = CategotyInListBinding.inflate(LayoutInflater.from(context), parentLayout, false)
        binding.categoryName.text = categoryList[categoryIndex]
        setCustomArrow(isExpanded, binding.arrowImage)

        return binding.root
    }

    override fun getChildView(categoryIndex: Int, itemIndex: Int, isExpanded: Boolean, convertView: View?, parentLayout: ViewGroup?): View {
        val binding = ItemInListBinding.inflate(LayoutInflater.from(context), parentLayout, false)
        binding.checkBox.text = ItemsList[categoryIndex][itemIndex].name

        return binding.root
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

    private fun setCustomArrow(isExpanded: Boolean, imageView: ImageView){
        if(isExpanded) imageView.setImageResource(R.drawable.baseline_keyboard_arrow_up_24)
        else imageView.setImageResource(R.drawable.baseline_keyboard_arrow_down_24)
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}