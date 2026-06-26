package com.example.gymtrackergod.ui.model

data class WorkoutSetUi(
    val id: Long = System.nanoTime(),
    var weight: Float = 0f,
    var reps: Int = 0
)