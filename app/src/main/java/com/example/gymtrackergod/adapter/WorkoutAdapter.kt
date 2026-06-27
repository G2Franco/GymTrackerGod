package com.example.gymtrackergod.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrackergod.data.`1`.entity.Exercise
import com.example.gymtrackergod.ui.model.WorkoutExercise
import com.example.gymtrackergod.ui.model.custom.WorkoutExerciseCard

class WorkoutAdapter(

    private val exercises: List<WorkoutExercise>,
    private val onAddSet: (Exercise) -> Unit,
    private val onDeleteSet: (Exercise, Int) -> Unit,
    private val onFinishExercise: (Exercise) -> Unit


) : RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {

    inner class ViewHolder(

        private val card: WorkoutExerciseCard

    ) : RecyclerView.ViewHolder(card) {

        fun bind(workoutExercise: WorkoutExercise) {

            card.setListeners(

                onAddSet,

                onDeleteSet,

                onFinishExercise,



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

    override fun getItemCount() =
        exercises.size


}
