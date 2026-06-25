package com.example.gymtrackergod.data.`1`.database

import com.example.gymtrackergod.data.`1`.entity.Exercise

object DatabaseSeeder {

    fun getExercises(): List<Exercise> {

        return listOf(

            // PUSH
            Exercise(day = "Push", name = "Peck Deck"),
            Exercise(day = "Push", name = "Press Banca"),
            Exercise(day = "Push", name = "Press Inclinado"),
            Exercise(day = "Push", name = "Press Militar"),
            Exercise(day = "Push", name = "Elevaciones Laterales"),
            Exercise(day = "Push", name = "Extension Triceps"),
            Exercise(day = "Push", name = "Extension Katana unilateral"),
            Exercise(day = "Push", name = "Press Frances"),

            // PULL
            Exercise(day = "Pull", name = "Jalon al Pecho"),
            Exercise(day = "Pull", name = "Remo para densidad"),
            Exercise(day = "Pull", name = "Remo Mancuerna"),
            Exercise(day = "Pull", name = "Jalon al Pecho para densidad"),
            Exercise(day = "Pull", name = "Curl Biceps"),
            Exercise(day = "Pull", name = "Curl Martillo"),

            // LEGS
            Exercise(day = "Legs", name = "Extension Cuadriceps"),
            Exercise(day = "Legs", name = "Sentadilla"),
            Exercise(day = "Legs", name = "Curl Femoral"),
            Exercise(day = "Legs", name = "Aductores"),
            Exercise(day = "Legs", name = "hip Thrust"),
        )
    }
}