package com.example.gymtrackergod.data.`1`.repository

import com.example.gymtrackergod.data.`1`.dao.ExerciseDao
import com.example.gymtrackergod.data.`1`.entity.Exercise

class ExerciseRepository(
    private val exerciseDao: ExerciseDao
) {

    suspend fun getExercisesByMuscles(
        muscles: List<String>
    ): List<Exercise> {

        return exerciseDao.getExercisesByMuscles(muscles)

    }

}