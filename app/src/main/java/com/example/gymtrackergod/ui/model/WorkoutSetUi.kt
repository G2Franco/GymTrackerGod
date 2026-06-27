package com.example.gymtrackergod.ui.model

data class WorkoutSetUi(

    val id: Long = System.nanoTime(),

    val weight: Float = 0f,

    val reps: Int = 0,

    val completed: Boolean = false,

    val saved: Boolean = false

)