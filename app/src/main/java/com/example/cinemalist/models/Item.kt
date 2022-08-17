package com.example.cinemalist.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val actors: List<Actor>,
    val directorName: String,
    val releaseYear: Int,
    val title: String
)