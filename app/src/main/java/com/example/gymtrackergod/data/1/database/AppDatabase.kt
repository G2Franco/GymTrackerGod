package com.example.gymtrackergod.data.`1`.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gymtrackergod.data.`1`.dao.ExerciseDao
import com.example.gymtrackergod.data.`1`.entity.Exercise
import com.example.gymtrackergod.data.`1`.entity.WorkoutSet
import com.example.gymtrackergod.data.`1`.dao.WorkoutSetDao


@Database(
    entities = [
        Exercise::class,
        WorkoutSet::class
    ],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutSetDao(): WorkoutSetDao
}