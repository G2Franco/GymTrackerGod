package com.example.gymtrackergod

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymtrackergod.adapter.WorkoutDayAdapter
import com.example.gymtrackergod.data.`1`.database.DatabaseProvider
import com.example.gymtrackergod.data.`1`.database.DatabaseSeeder
import com.example.gymtrackergod.databinding.ActivityMainBinding
import com.example.gymtrackergod.model.WorkoutDay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val database = DatabaseProvider.getDatabase(this)

        lifecycleScope.launch {

            val count = database.exerciseDao().getCount()

            if (count == 0) {

                database.exerciseDao().insertAll(
                    DatabaseSeeder.getExercises()
                )
            }
        }
        val workoutDays = listOf(
            WorkoutDay("Pecho", "🔥"),
            WorkoutDay("Espalda", "🏋️‍♂️"),
            WorkoutDay("Pierna", "🦵"),
            WorkoutDay("Hombro","💪" ),
            WorkoutDay("Biceps", "💪"),
            WorkoutDay("Triceps", "💪"),
        )

        binding.rvWorkoutDays.layoutManager =
            LinearLayoutManager(this)

        binding.rvWorkoutDays.adapter =
            WorkoutDayAdapter(workoutDays) { day ->

                val intent =
                    Intent(this, ExerciseActivity::class.java)

                intent.putExtra("DAY_NAME", day.name)

                startActivity(intent)
            }
    }
}