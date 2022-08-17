package com.example.cinemalist.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cinemalist.models.Item

@Dao
interface ItemDao {

    @Query("SELECT * FROM items")
    suspend fun getAllItem(): LiveData<List<Item>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Item)

    @Delete
    suspend fun delete(item: Item)

}