package com.example.gymtrackergod.data.`1`.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gymtrackergod.data.`1`.dao.ExerciseDao
import com.example.gymtrackergod.data.`1`.dao.WorkoutSessionDao
import com.example.gymtrackergod.data.`1`.entity.Exercise
import com.example.gymtrackergod.data.`1`.entity.WorkoutSet
import com.example.gymtrackergod.data.`1`.dao.WorkoutSetDao
import com.example.gymtrackergod.data.`1`.entity.WorkoutSession


@Database(
    entities = [
        Exercise::class,
        WorkoutSet::class,
        WorkoutSession::class

    ],
    version = 6
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutSetDao(): WorkoutSetDao
    abstract fun workoutSessionDao(): WorkoutSessionDao
}