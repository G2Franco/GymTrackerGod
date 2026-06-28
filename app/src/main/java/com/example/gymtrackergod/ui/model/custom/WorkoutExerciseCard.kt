package com.example.gymtrackergod.ui.model.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymtrackergod.adapter.WorkoutSetAdapter
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

    private var onSaveExercise:
            ((Exercise, List<WorkoutSetUi>) -> Unit)? = null

    private lateinit var workoutExercise: WorkoutExercise


    private val binding =
        ViewWorkoutExerciseCardBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
 private lateinit var setAdapter: WorkoutSetAdapter


    init {

        setAdapter = WorkoutSetAdapter { index ->

            onDeleteSet?.invoke(
                workoutExercise.exercise,
                index
            )

        }

        binding.rvSets.layoutManager =
            LinearLayoutManager(context)

        binding.rvSets.adapter =
            setAdapter

    }

    fun bind(workoutExercise: WorkoutExercise) {

        this.workoutExercise = workoutExercise
        binding.txtExerciseName.text =
            workoutExercise.exercise.name
        setAdapter.updateSets(workoutExercise.sets)
        binding.btnAddSet.setOnClickListener {

            onAddSet?.invoke(
                workoutExercise.exercise
            )

        }

        binding.btnFinishExercise.setOnClickListener {

            onSaveExercise?.invoke(

                workoutExercise.exercise,

                setAdapter.getSets()

            )

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

}