package com.example.gymtrackergod.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrackergod.data.`1`.entity.Exercise
import com.example.gymtrackergod.ui.model.WorkoutExercise
import com.example.gymtrackergod.ui.model.WorkoutSetUi
import com.example.gymtrackergod.ui.model.custom.WorkoutExerciseCard


class WorkoutAdapter(

    private val onAddSet: (Exercise) -> Unit,
    private val onDeleteSet: (Exercise, Int) -> Unit,
    private val onSaveExercise: (Exercise, List<WorkoutSetUi>) -> Unit

) : RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {

    private val exercises = mutableListOf<WorkoutExercise>()

    inner class ViewHolder(

        private val card: WorkoutExerciseCard

    ) : RecyclerView.ViewHolder(card) {

        fun bind(workoutExercise: WorkoutExercise) {

            card.setListeners(

                onAddSet,
                onDeleteSet,
                onSaveExercise

            )

            card.bind(workoutExercise)

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        return ViewHolder(
            WorkoutExerciseCard(parent.context)
        )

    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        holder.bind(
            exercises[position]
        )

    }

    override fun getItemCount(): Int = exercises.size

    fun submitExercises(newExercises: List<WorkoutExercise>) {

        exercises.clear()

        exercises.addAll(newExercises)

        notifyDataSetChanged()

    }

}


