package com.example.test04

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {
    abstract fun itemDao() : ItemDao



    companion object {
        private var instance : ItemDatabase? = null
        private val dbName = "item-database.db"

        fun getInstance(context: Context) : ItemDatabase? {
            if(instance == null){
                synchronized(ItemDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ItemDatabase::class.java,
                        dbName
                    ).createFromAsset("item_db.db").build()
                }
            }
            return instance
        }
    }
}