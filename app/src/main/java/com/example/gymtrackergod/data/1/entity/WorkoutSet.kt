package com.example.gymtrackergod.data.`1`.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_set")
data class WorkoutSet(

    @PrimaryKey(autoGenerate = true)
    val id:Int=0,

    val sessionId:Int,

    val exerciseId:Int,

    val weight:Float,

    val reps:Int,

    val date:Long

)