package com.example.gymtrackergod.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrackergod.data.`1`.entity.Exercise
import com.example.gymtrackergod.ui.model.WorkoutExercise
import com.example.gymtrackergod.ui.model.WorkoutSetUi
import com.example.gymtrackergod.ui.model.custom.WorkoutExerciseCard


class WorkoutAdapter(

    private val onAddSet: (Exercise, List<WorkoutSetUi>) -> Unit,
    private val onDeleteSet: (Exercise, Int) -> Unit,
    private val onSaveExercise: (Exercise, List<WorkoutSetUi>) -> Unit

) : ListAdapter<WorkoutExercise, WorkoutAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(
        private val card: WorkoutExerciseCard
    ) : RecyclerView.ViewHolder(card) {

        fun bind(item: WorkoutExercise) {

            card.setListeners(

                onAddSet,
                onDeleteSet,
                onSaveExercise

            )

            card.bind(item)

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

        holder.bind(getItem(position))

    }

    class DiffCallback : DiffUtil.ItemCallback<WorkoutExercise>() {

        override fun areItemsTheSame(
            oldItem: WorkoutExercise,
            newItem: WorkoutExercise
        ): Boolean {

            return oldItem.exercise.id == newItem.exercise.id

        }

        override fun areContentsTheSame(
            oldItem: WorkoutExercise,
            newItem: WorkoutExercise
        ): Boolean {

            return oldItem == newItem

        }

    }

}


