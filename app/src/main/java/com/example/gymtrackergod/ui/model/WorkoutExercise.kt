package com.example.gymtrackergod.ui.model

import com.example.gymtrackergod.data.`1`.entity.Exercise


data class WorkoutExercise(

    val exercise: Exercise,

    val sets: MutableList<WorkoutSetUi> = mutableListOf(),

    val saved: Boolean = false

)