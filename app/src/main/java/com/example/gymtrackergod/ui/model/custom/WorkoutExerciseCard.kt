
package com.example.gymtrackergod.ui.model.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymtrackergod.adapter.WorkoutSetAdapter
import com.example.gymtrackergod.data.`1`.entity.Exercise
import com.example.gymtrackergod.databinding.ViewWorkoutExerciseCardBinding
import com.example.gymtrackergod.ui.model.WorkoutExercise
import com.example.gymtrackergod.ui.model.WorkoutSetUi
import com.example.gymtrackergod.databinding.ViewWorkoutSetBinding


class WorkoutExerciseCard @JvmOverloads constructor(

    context: Context,
    attrs: AttributeSet? = null

) : LinearLayout(context, attrs) {

    private var onAddSet: ((Exercise, List<WorkoutSetUi>) -> Unit)? = null

    private var onDeleteSet: ((Exercise, Int) -> Unit)? = null

    private var onSaveExercise:
            ((Exercise, List<WorkoutSetUi>) -> Unit)? = null

    private lateinit var workoutExercise: WorkoutExercise

    private val binding =
        ViewWorkoutExerciseCardBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )

    fun bind(workoutExercise: WorkoutExercise) {

        this.workoutExercise = workoutExercise

        binding.txtExerciseName.text =
            workoutExercise.exercise.name

        binding.layoutSets.removeAllViews()

        workoutExercise.sets.forEachIndexed { index, set ->

            addSetView(index, set)

        }

        binding.btnAddSet.setOnClickListener {

            onAddSet?.invoke(

                workoutExercise.exercise,

                getWorkoutSets()

            )

        }
        binding.btnFinishExercise.setOnClickListener {

            onSaveExercise?.invoke(

                workoutExercise.exercise,

                getWorkoutSets()

            )

        }

        if (workoutExercise.saved) {

            binding.btnFinishExercise.text = "✓ Guardado"

            binding.btnFinishExercise.isEnabled = false

        } else {

            binding.btnFinishExercise.text = "💾 Guardar ejercicio"

            binding.btnFinishExercise.isEnabled = true

        }



    }

    private fun addSetView(
        index: Int,
        set: WorkoutSetUi
    ) {

        val setBinding =
            ViewWorkoutSetBinding.inflate(
                LayoutInflater.from(context),
                binding.layoutSets,
                false
            )

        setBinding.txtSet.text =
            "Serie ${index + 1}"

        if (set.weight != 0f) {

            setBinding.edtWeight.setText(
                set.weight.toString()
            )

        }

        if (set.reps != 0) {

            setBinding.edtReps.setText(
                set.reps.toString()
            )

        }

        if (workoutExercise.saved) {

            setBinding.edtWeight.isEnabled = false
            setBinding.edtReps.isEnabled = false
            setBinding.btnDelete.isEnabled = false

        }


        setBinding.btnDelete.setOnClickListener {

            onDeleteSet?.invoke(
                workoutExercise.exercise,
                index
            )

        }

        binding.layoutSets.addView(
            setBinding.root
        )

    }

    private fun getWorkoutSets(): List<WorkoutSetUi> {

        val sets =
            mutableListOf<WorkoutSetUi>()

        for (i in 0 until binding.layoutSets.childCount) {

            val view =
                binding.layoutSets.getChildAt(i)

            val setBinding =
                ViewWorkoutSetBinding.bind(view)

            val weight =
                setBinding.edtWeight.text
                    .toString()
                    .toFloatOrNull() ?: 0f

            val reps =
                setBinding.edtReps.text
                    .toString()
                    .toIntOrNull() ?: 0

            sets.add(

                WorkoutSetUi(

                    weight = weight,

                    reps = reps,

                    saved = true

                )

            )

        }

        return sets

    }

    fun setListeners(

        onAddSet: (Exercise, List<WorkoutSetUi>) -> Unit,


        onDeleteSet: (Exercise, Int) -> Unit,

        onSaveExercise: (Exercise, List<WorkoutSetUi>) -> Unit

    ) {

        this.onAddSet = onAddSet

        this.onDeleteSet = onDeleteSet

        this.onSaveExercise = onSaveExercise

    }

}

