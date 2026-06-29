package com.example.gymtrackergod.data.`1`.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.gymtrackergod.data.`1`.entity.WorkoutSession

@Dao
interface WorkoutSessionDao {

    @Insert
    suspend fun insert(
        session: WorkoutSession
    ): Long

    @Update
    suspend fun update(
        session: WorkoutSession
    )

    @Query("""
        SELECT *
        FROM workout_session
        ORDER BY startTime DESC
    """)
    suspend fun getAll(): List<WorkoutSession>
}