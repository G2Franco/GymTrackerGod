package com.example.gymtrackergod.data.`1`.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.gymtrackergod.data.`1`.entity.WorkoutSession

@Dao
interface WorkoutSessionDao {

    @Insert
    suspend fun insert(
        session: WorkoutSession
    ): Long

    @Query("""
        SELECT *
        FROM workout_session
        ORDER BY date DESC
    """)
    suspend fun getAll(): List<WorkoutSession>

    @Query("""        
        SELECT *
        FROM workout_session
        WHERE id = :id
    """)
    suspend fun getById(
        id: Long
    ): WorkoutSession?
}