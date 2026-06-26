package com.example.gymtrackergod.ui.model

import com.example.gymtrackergod.data.`1`.entity.Exercise

data class ExerciseSelection(

    val exercise: Exercise,

    val selected: Boolean = false

)