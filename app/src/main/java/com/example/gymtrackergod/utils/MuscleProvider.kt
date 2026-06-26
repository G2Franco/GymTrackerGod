package com.example.gymtrackergod.utils

import com.example.gymtrackergod.ui.model.Muscle

object MuscleProvider {

    fun getMuscles() = listOf(

        Muscle("Pecho"),

        Muscle("Espalda"),

        Muscle("Hombro"),

        Muscle("Biceps"),

        Muscle("Triceps"),

        Muscle("Pierna")
    )

}