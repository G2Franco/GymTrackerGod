package com.example.gymtrackergod.ui.model.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.gymtrackergod.data.`1`.entity.Exercise
import com.example.gymtrackergod.databinding.ViewWorkoutExerciseCardBinding
import com.example.gymtrackergod.databinding.ViewWorkoutSetBinding
import com.example.gymtrackergod.ui.model.WorkoutExercise
import com.example.gymtrackergod.ui.model.WorkoutSetUi


class WorkoutExerciseCard @JvmOverloads constructor(

    context: Context,
    attrs: AttributeSet? = null


) : LinearLayout(context, attrs) {
    private var onAddSet: ((Exercise) -> Unit)? = null

    private var onDeleteSet: ((Exercise, Int) -> Unit)? = null

    private var onFinishExercise: ((Exercise) -> Unit)? = null
    private lateinit var workoutExercise: WorkoutExercise

    private var onSaveExercise:
            ((Exercise, List<WorkoutSetUi>) -> Unit)? = null

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

        renderSets(workoutExercise)

        binding.btnAddSet.setOnClickListener {

            onAddSet?.invoke(
                workoutExercise.exercise
            )

        }

        binding.btnFinishExercise.setOnClickListener {

            onSaveExercise?.invoke(

                workoutExercise.exercise,

                getWorkoutSets()

            )

        }

    }
    private fun renderSets(
        workoutExercise: WorkoutExercise
    ) {

        binding.layoutSets.removeAllViews()

        workoutExercise.sets.forEachIndexed { index, set ->

            val setBinding = ViewWorkoutSetBinding.inflate(
                LayoutInflater.from(context),
                binding.layoutSets,
                false
            )

            setBinding.txtSet.text = "Serie ${index + 1}"

            if (set.weight != 0f) {
                setBinding.edtWeight.setText(set.weight.toString())
            }

            if (set.reps != 0) {
                setBinding.edtReps.setText(set.reps.toString())
            }

            // 👇 ESTE CÓDIGO VA AQUÍ
            setBinding.btnDelete.setOnClickListener {

                onDeleteSet?.invoke(
                    workoutExercise.exercise,
                    index
                )

            }

            binding.layoutSets.addView(setBinding.root)

        }

    }
    fun setListeners(

        onAddSet: (Exercise) -> Unit,

        onDeleteSet: (Exercise, Int) -> Unit,

        onSaveExercise: (Exercise, List<WorkoutSetUi>) -> Unit


    ) {

        this.onAddSet = onAddSet
        this.onDeleteSet = onDeleteSet
        this.onSaveExercise = onSaveExercise
    }
    private fun getWorkoutSets(): List<WorkoutSetUi> {

        val sets = mutableListOf<WorkoutSetUi>()

        for (i in 0 until binding.layoutSets.childCount) {

            val view = binding.layoutSets.getChildAt(i)

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


}
