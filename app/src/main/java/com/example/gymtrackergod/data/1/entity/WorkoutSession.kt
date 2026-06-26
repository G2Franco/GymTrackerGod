package com.example.gymtrackergod.data.`1`.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_session")
data class WorkoutSession(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val startTime: Long,

    val endTime: Long? = null,

    val workoutName: String
)