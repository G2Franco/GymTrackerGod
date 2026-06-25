package com.example.gymtrackergod.data.`1`.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise")
data class Exercise(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val day: String,

    val name: String
)