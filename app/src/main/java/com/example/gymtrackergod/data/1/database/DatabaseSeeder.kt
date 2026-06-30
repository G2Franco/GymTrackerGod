package com.example.gymtrackergod.data.`1`.database

import com.example.gymtrackergod.data.`1`.entity.Exercise

object DatabaseSeeder {

    fun getExercises(): List<Exercise> {

        return listOf(

            // Pecho
            Exercise(day = "Pecho", name = "Peck Deck"),
            Exercise(day = "Pecho", name = "Press Banca con Barra"),
            Exercise(day = "Pecho", name = "Press Inclinado con Barra"),
            Exercise(day = "Pecho", name = "Press Banca con Mancuernas"),
            Exercise(day = "Pecho", name = "Press Inclinado con Mancuernas"),
            // Triceps
            Exercise(day = "Triceps", name = "Extension Triceps"),
            Exercise(day = "Triceps", name = "Extension Katana unilateral"),
            Exercise(day = "Triceps", name = "Extension Katana Bilateral"),
            Exercise(day = "Triceps", name = "Press Frances"),

            // Hombro
            Exercise(day = "Hombro", name = "Press Militar"),
            Exercise(day = "Hombro", name = "Elevaciones Laterales Cuerda"),
            Exercise(day = "Hombro", name = "Elevaciones Laterales Mancuerna"),
            Exercise(day = "Hombro", name = "Face Pull"),
            Exercise(day = "Hombro", name = "Extension Laterales para Hombro Posterior"),

            // Espalda
            Exercise(day = "Espalda", name = "Jalon al Pecho"),
            Exercise(day = "Espalda", name = "Remo para densidad"),
            Exercise(day = "Espalda", name = "Remo Mancuerna para dorsal"),
            Exercise(day = "Espalda", name = "Jalon al Pecho para densidad"),

            // Biceps
            Exercise(day = "Biceps", name = "Curl Biceps"),
            Exercise(day = "Biceps", name = "Curl Biceps Martillo"),
            Exercise(day = "Biceps", name = "Curl Biceps con Barra Z"),
            Exercise(day = "Biceps", name = "Curl Biceps Predicador"),

            // Pierna
            Exercise(day = "Pierna", name = "Extension Cuadriceps"),
            Exercise(day = "Pierna", name = "Sentadilla"),
            Exercise(day = "Pierna", name = "Curl Femoral"),
            Exercise(day = "Pierna", name = "Aductores"),
            Exercise(day = "Pierna", name = "hip Thrust"),
            Exercise(day = "Pierna", name = "Peso Muerto"),
            Exercise(day = "Pierna", name = "Prensa"),
            Exercise(day = "Pierna", name = "Bulgaras")
        )
    }
}