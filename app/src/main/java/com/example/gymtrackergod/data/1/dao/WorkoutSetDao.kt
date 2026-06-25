package com.example.gymtrackergod.data.`1`.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.gymtrackergod.data.`1`.entity.WorkoutSet


@Dao
interface WorkoutSetDao {

    @Insert
    suspend fun insert(workoutSet: WorkoutSet)
    @Query("""
    SELECT *
    FROM workout_set
    WHERE exerciseId = :exerciseId
    ORDER BY id DESC
""")
    suspend fun getHistory(
        exerciseId: Int
    ): List<WorkoutSet>

    @Query("""
    SELECT *
    FROM workout_set
    WHERE exerciseId = :exerciseId
    ORDER BY id DESC
    LIMIT 1
""")
    suspend fun getLastSet(
        exerciseId: Int
    ): WorkoutSet?
    @Query("""
    SELECT MAX(weight)
    FROM workout_set
    WHERE exerciseId = :exerciseId
""")
    suspend fun getPersonalRecord(
        exerciseId: Int
    ): Float?

    @Query("""
SELECT SUM(weight * reps)
FROM workout_set
WHERE exerciseId = :exerciseId
""")
    suspend fun getVolume(
        exerciseId: Int
    ): Float?
    @Delete
    suspend fun delete(
        workoutSet: WorkoutSet
    )
    @Query("""
    SELECT MAX(weight)
    FROM workout_set
    WHERE exerciseId = :exerciseId
""")
    suspend fun getPr(
        exerciseId: Int
    ): Float?

    @Query("""
    SELECT SUM(weight * reps)
    FROM workout_set
    WHERE exerciseId = :exerciseId
""")
    suspend fun getTotalVolume(
        exerciseId: Int
    ): Float?

    @Query("""
    SELECT COUNT(*)
    FROM workout_set
    WHERE exerciseId = :exerciseId
""")
    suspend fun getTotalSets(
        exerciseId: Int
    ): Int

    @Query("""
    SELECT MAX(date)
    FROM workout_set
    WHERE exerciseId = :exerciseId
""")
    suspend fun getLastWorkoutDate(
        exerciseId: Int
    ): Long?
    @Query("""
    SELECT *
    FROM workout_set
    WHERE exerciseId = :exerciseId
    ORDER BY date ASC
""")
    suspend fun getAllSets(
        exerciseId: Int
    ): List<WorkoutSet>
}