package com.example.gymtrackergod.data.`1`.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_session")
data class WorkoutSession(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val dayName: String,

    val date: Long
)