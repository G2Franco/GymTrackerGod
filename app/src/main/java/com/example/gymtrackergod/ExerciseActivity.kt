package com.example.gymtrackergod

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymtrackergod.adapter.ExerciseAdapter
import com.example.gymtrackergod.data.`1`.database.DatabaseProvider
import kotlinx.coroutines.launch
import com.example.gymtrackergod.databinding.ActivityExerciseBinding
class ExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExerciseBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val dayName =
            intent.getStringExtra("DAY_NAME") ?: ""

        binding.txtTitle.text = dayName

        binding.rvExercises.layoutManager =
            LinearLayoutManager(this)

        val database =
            DatabaseProvider.getDatabase(this)

        lifecycleScope.launch {

            val exercises =
                database.exerciseDao()
                    .getExercisesByDay(dayName)

            binding.rvExercises.adapter =
                ExerciseAdapter(exercises) { exercise ->

                    val intent = Intent(
                        this@ExerciseActivity,
                        WorkoutSetActivity::class.java
                    )

                    intent.putExtra("EXERCISE_ID", exercise.id)
                    intent.putExtra("EXERCISE_NAME", exercise.name)

                    startActivity(intent)
                }
        }
    }
}