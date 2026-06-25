package com.example.gymtrackergod

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymtrackergod.adapter.WorkoutSetAdapter
import com.example.gymtrackergod.data.`1`.database.DatabaseProvider
import com.example.gymtrackergod.data.`1`.entity.WorkoutSet
import com.example.gymtrackergod.databinding.ActivityWorkoutSetBinding
import kotlinx.coroutines.launch

class WorkoutSetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWorkoutSetBinding

    private var exerciseId = 0
    private var exerciseName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            ActivityWorkoutSetBinding.inflate(layoutInflater)

        setContentView(binding.root)

        exerciseId =
            intent.getIntExtra("EXERCISE_ID", 0)

        exerciseName =
            intent.getStringExtra("EXERCISE_NAME") ?: ""

        binding.txtExerciseName.text =
            exerciseName

        binding.rvSets.layoutManager =
            LinearLayoutManager(this)

        loadLastWeight()
        loadHistory()
        loadPR()
        loadVolume()

        binding.btnAddSet.setOnClickListener {

            saveSet()
        }

        binding.btnFinish.setOnClickListener {

            finish()
        }
        binding.btnHistory.setOnClickListener {

            val intent =
                Intent(
                    this,
                    HistoryActivity::class.java
                )

            intent.putExtra(
                "EXERCISE_ID",
                exerciseId
            )

            intent.putExtra(
                "EXERCISE_NAME",
                exerciseName
            )

            startActivity(intent)
        }
        binding.btnStatistics.setOnClickListener {

            val intent =
                Intent(
                    this,
                    StatisticsActivity::class.java
                )

            intent.putExtra(
                "EXERCISE_ID",
                exerciseId
            )

            intent.putExtra(
                "EXERCISE_NAME",
                exerciseName
            )

            startActivity(intent)
        }
    }

    private fun saveSet() {

        val weightText = binding.etWeight.text.toString()
        val repsText = binding.etReps.text.toString()

// 1. Validación de campos vacíos
        if (weightText.isBlank() || repsText.isBlank()) {
            return
        }

        val weightVal = weightText.toFloat()
        val repsVal = repsText.toInt()

// 2. Validación de límites máximos
        if (weightVal > 500) {
            binding.etWeight.error = "El peso no puede ser mayor a 500 KG"
            return
        }

        if (repsVal > 30) {
            binding.etReps.error = "Las repeticiones no pueden ser mayores a 30"
            return
        }

        val workoutSet =
            WorkoutSet(
                exerciseId = exerciseId,
                weight = weightText.toFloat(),
                reps = repsText.toInt(),
                date = System.currentTimeMillis()
            )

        lifecycleScope.launch {

            DatabaseProvider
                .getDatabase(this@WorkoutSetActivity)
                .workoutSetDao()
                .insert(workoutSet)

            binding.etWeight.text?.clear()
            binding.etReps.text?.clear()

            loadHistory()
            loadPR()
            loadVolume()
        }
    }

    private fun loadHistory() {

        lifecycleScope.launch {

            val sets =
                DatabaseProvider
                    .getDatabase(this@WorkoutSetActivity)
                    .workoutSetDao()
                    .getHistory(exerciseId)

            binding.rvSets.adapter =
                WorkoutSetAdapter(sets) { set ->

                    AlertDialog.Builder(this@WorkoutSetActivity)
                        .setTitle("Eliminar serie")
                        .setMessage("¿Deseas eliminar esta serie?")
                        .setPositiveButton("Eliminar") { _, _ ->

                            lifecycleScope.launch {

                                DatabaseProvider
                                    .getDatabase(this@WorkoutSetActivity)
                                    .workoutSetDao()
                                    .delete(set)

                                loadHistory()
                                loadPR()
                                loadVolume()
                            }
                        }
                        .setNegativeButton("Cancelar", null)
                        .show()
                }
        }
    }
    private fun loadLastWeight() {

        lifecycleScope.launch {

            val lastSet =
                DatabaseProvider
                    .getDatabase(this@WorkoutSetActivity)
                    .workoutSetDao()
                    .getLastSet(exerciseId)

            lastSet?.let {

                binding.etWeight.setText(
                    it.weight.toString()
                )
            }
        }
    }
    private fun loadPR() {

        lifecycleScope.launch {

            val pr =
                DatabaseProvider
                    .getDatabase(this@WorkoutSetActivity)
                    .workoutSetDao()
                    .getPersonalRecord(exerciseId)

            binding.txtPr.text =
                "🏆 PR: ${pr ?: 0f} kg"
        }
    }
    private fun loadVolume() {

        lifecycleScope.launch {

            val volume =
                DatabaseProvider
                    .getDatabase(this@WorkoutSetActivity)
                    .workoutSetDao()
                    .getVolume(exerciseId)

            binding.txtVolume.text =
                "📊 Volumen: ${volume ?: 0f} kg"
        }
    }
}