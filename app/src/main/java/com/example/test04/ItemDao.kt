package com.example.test04

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ItemDao {
    @Query("SELECT * FROM Item WHERE category = :category") // 매개변수로 category 넣을 시 db에서 해당하는 item을 리스트로 모두 가져온다.
    fun getAllOfThisCategory(category : String): MutableList<Item>

    @Insert
    fun insert(item: Item)

    @Update
    fun update(item: Item)

    @Delete
    fun delete(item: Item)
}