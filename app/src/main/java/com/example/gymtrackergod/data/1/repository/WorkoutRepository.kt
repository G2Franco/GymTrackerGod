package com.example.gymtrackergod.data.`1`.repository

import com.example.gymtrackergod.data.`1`.dao.WorkoutSessionDao
import com.example.gymtrackergod.data.`1`.dao.WorkoutSetDao
import com.example.gymtrackergod.data.`1`.entity.WorkoutSession
import com.example.gymtrackergod.data.`1`.entity.WorkoutSet
import com.example.gymtrackergod.ui.model.ExerciseHistory
import com.example.gymtrackergod.ui.model.WorkoutExercise


class WorkoutRepository(

    private val sessionDao: WorkoutSessionDao,

    private val setDao: WorkoutSetDao

) {

    suspend fun saveWorkout(

        session: WorkoutSession,

        exercises: List<WorkoutExercise>

    ) {

        val sessionId =
            sessionDao.insert(session).toInt()

        exercises.forEach { exercise ->

            exercise.sets.forEach { set ->

                setDao.insert(

                    WorkoutSet(

                        sessionId = sessionId,

                        exerciseId = exercise.exercise.id,

                        weight = set.weight,

                        reps = set.reps,
                        date = System.currentTimeMillis()

                    )

                )

            }

        }

    }

    suspend fun getHistory(
        exerciseId: Int
    ): ExerciseHistory {

        val last =
            setDao.getLastSet(exerciseId)

        val pr =
            setDao.getPr(exerciseId) ?: 0f

        val volume =
            setDao.getTotalVolume(exerciseId) ?: 0f

        val totalSets =
            setDao.getTotalSets(exerciseId)

        val lastWorkout =

            if (last == null) {

                "Sin historial"

            } else {

                "${last.weight} kg x ${last.reps}"

            }

        return ExerciseHistory(

            lastWorkout = lastWorkout,

            personalRecord = pr,

            totalVolume = volume,

            totalSets = totalSets

        )

    }

}

