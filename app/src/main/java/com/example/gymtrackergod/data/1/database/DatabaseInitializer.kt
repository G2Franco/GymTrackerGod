package com.example.gymtrackergod.data.`1`.database

object DatabaseInitializer {

    suspend fun initialize(database: AppDatabase) {

        if (database.exerciseDao().count() == 0) {

            database.exerciseDao().insertAll(
                DatabaseSeeder.getExercises()
            )

        }

    }
}