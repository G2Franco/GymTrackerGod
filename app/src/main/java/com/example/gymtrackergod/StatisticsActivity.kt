package com.example.gymtrackergod

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.gymtrackergod.data.`1`.database.DatabaseProvider
import com.example.gymtrackergod.databinding.ActivityStatisticsBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


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
}