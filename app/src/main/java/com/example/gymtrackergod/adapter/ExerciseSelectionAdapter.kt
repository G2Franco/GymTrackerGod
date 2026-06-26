package com.example.gymtrackergod.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrackergod.databinding.ItemSelectExerciseBinding
import com.example.gymtrackergod.ui.model.ExerciseSelection

class ExerciseSelectionAdapter(
    private val onClick: (ExerciseSelection) -> Unit
) : ListAdapter<
        ExerciseSelection,
        ExerciseSelectionAdapter.ViewHolder
        >(DiffCallback()) {

    inner class ViewHolder(
        private val binding: ItemSelectExerciseBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ExerciseSelection) {

            binding.checkExercise.text =
                item.exercise.name

            binding.checkExercise.isChecked =
                item.selected

            binding.checkExercise.setOnClickListener {

                onClick(item)

            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding =
            ItemSelectExerciseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        holder.bind(getItem(position))

    }

    class DiffCallback :
        DiffUtil.ItemCallback<ExerciseSelection>() {

        override fun areItemsTheSame(
            oldItem: ExerciseSelection,
            newItem: ExerciseSelection
        ): Boolean {

            return oldItem.exercise.id ==
                    newItem.exercise.id
        }

        override fun areContentsTheSame(
            oldItem: ExerciseSelection,
            newItem: ExerciseSelection
        ): Boolean {

            return oldItem == newItem
        }
    }
}