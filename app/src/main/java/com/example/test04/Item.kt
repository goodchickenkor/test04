package com.example.test04

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Item")
data class Item (
    val defaultItem : Int?, //0은 false, 1은 true
    val name : String?,
    val category : String?,
    @PrimaryKey(autoGenerate = true)val id : Int
) : Parcelable