package com.example.gymtrackergod.data.`1`.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.gymtrackergod.data.`1`.entity.Exercise

@Dao
interface ExerciseDao {

    @Insert
    suspend fun insert(exercise: Exercise)

    @Insert
    suspend fun insertAll(exercises: List<Exercise>)

    @Query("SELECT * FROM exercise WHERE day = :day")
    suspend fun getExercisesByDay(day: String): List<Exercise>

    @Query("SELECT COUNT(*) FROM exercise")
    suspend fun getCount(): Int
}