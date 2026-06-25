package com.example.gymtrackergod

import android.R.attr.entries
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.gymtrackergod.data.`1`.database.DatabaseProvider
import com.example.gymtrackergod.data.`1`.entity.WorkoutSet
import com.example.gymtrackergod.databinding.ActivityStatisticsBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlin.collections.mapIndexed

class StatisticsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStatisticsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            ActivityStatisticsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val exerciseId =
            intent.getIntExtra(
                "EXERCISE_ID",
                0
            )

        val exerciseName =
            intent.getStringExtra(
                "EXERCISE_NAME"
            ) ?: ""

        binding.txtTitle.text =
            "📊 $exerciseName"

        loadStats(exerciseId)
    }

    private fun loadStats(
        exerciseId: Int
    ) {

        lifecycleScope.launch {

            val dao =
                DatabaseProvider
                    .getDatabase(this@StatisticsActivity)
                    .workoutSetDao()

            val pr =
                dao.getPr(exerciseId)

            val volume =
                dao.getTotalVolume(exerciseId)

            val sets =
                dao.getTotalSets(exerciseId)

            val lastDate =
                dao.getLastWorkoutDate(exerciseId)

            val allSets =
                dao.getAllSets(exerciseId)

            showPrChart(allSets)


            binding.txtPr.text =
                "🏆 PR: ${pr ?: 0f} kg"

            binding.txtVolume.text =
                "📈 Volumen total: ${volume ?: 0f} kg"

            binding.txtSets.text =
                "🔥 Series registradas: $sets"

            val formattedDate =
                lastDate?.let {
                    SimpleDateFormat(
                        "dd/MM/yyyy",
                        Locale.getDefault()
                    ).format(Date(it))
                } ?: "-"

            binding.txtLastWorkout.text =
                "📅 Último entrenamiento: $formattedDate"
        }

    }

    private fun showPrChart(
        sets: List<WorkoutSet>
    ) {

        binding.lineChart.description.isEnabled = false

        binding.lineChart.axisRight.isEnabled = false

        binding.lineChart.legend.textColor = Color.WHITE

        binding.lineChart.xAxis.textColor = Color.WHITE

        binding.lineChart.axisLeft.textColor = Color.WHITE

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

        val dailyRecords =
            grouped.map { entry ->

                entry.value.maxOf {
                    it.weight
                }
            }

        val entries =
            dailyRecords.mapIndexed { index, pr ->

                Entry(
                    index.toFloat(),
                    pr
                )
            }

        val dataSet =
            LineDataSet(
                entries,
                "PR por entrenamiento"
            )
        dataSet.color =
            Color.parseColor("#BB86FC")

        dataSet.setCircleColor(
            Color.parseColor("#BB86FC")
        )

        dataSet.valueTextColor =
            Color.WHITE

        dataSet.lineWidth = 3f

        dataSet.circleRadius = 6f

        val lineData =
            LineData(
                dataSet
            )

        binding.lineChart.data =
            lineData

        binding.lineChart.invalidate()
    }
}