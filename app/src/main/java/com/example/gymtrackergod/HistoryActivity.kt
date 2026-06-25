package com.example.gymtrackergod

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymtrackergod.adapter.HistoryAdapter
import com.example.gymtrackergod.data.`1`.database.DatabaseProvider
import com.example.gymtrackergod.databinding.ActivityHistoryBinding
import com.example.gymtrackergod.model.HistoryItem
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val exerciseName =
            intent.getStringExtra("EXERCISE_NAME") ?: ""

        binding.txtTitle.text =
            "Historial - $exerciseName"

        binding.rvHistory.layoutManager =
            LinearLayoutManager(this)
        val exerciseId =
            intent.getIntExtra("EXERCISE_ID", 0)

        loadHistory(exerciseId)
    }
    private fun loadHistory(
        exerciseId: Int
    ) {

        lifecycleScope.launch {

            val sets =
                DatabaseProvider
                    .getDatabase(this@HistoryActivity)
                    .workoutSetDao()
                    .getHistory(exerciseId)

            val formatter =
                SimpleDateFormat(
                    "dd/MM/yyyy",
                    Locale.getDefault()
                )

            val grouped =
                sets.groupBy {

                    formatter.format(
                        Date(it.date)
                    )
                }

            val historyItems =
                grouped.map { entry ->

                    val maxWeight =
                        entry.value.maxOf {
                            it.weight
                        }

                    val volume =
                        entry.value.sumOf {
                            (it.weight * it.reps).toDouble()
                        }.toFloat()

                    val totalSets =
                        entry.value.size

                    HistoryItem(
                        date = entry.key,
                        maxWeight = maxWeight,
                        volume = volume,
                        totalSets = totalSets,
                        sets = entry.value.mapIndexed { index, set ->

                            "Serie ${index + 1}: ${set.weight} kg x ${set.reps}"
                        }
                    )
                }

            binding.rvHistory.adapter =
                HistoryAdapter(
                    historyItems.sortedByDescending {
                        SimpleDateFormat(
                            "dd/MM/yyyy",
                            Locale.getDefault()
                        ).parse(it.date)
                    }
                )
        }
    }
}