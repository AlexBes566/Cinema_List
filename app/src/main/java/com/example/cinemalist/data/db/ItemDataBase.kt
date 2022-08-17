package com.example.cinemalist.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class ItemDataBase: RoomDatabase() {

    abstract fun getItemDao(): ItemDao

    companion object{
        @Volatile
        private var instance: ItemDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context): ItemDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                ItemDataBase::class.java,
                "item_database"
            ).build()
        }
    }
}