package com.example.gymtrackergod.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrackergod.databinding.ViewWorkoutExerciseCardBinding
import com.example.gymtrackergod.databinding.ViewWorkoutSetBinding

import com.example.gymtrackergod.ui.model.WorkoutSetUi

class WorkoutSetAdapter(

    private val onDeleteSet: (Int) -> Unit


) : RecyclerView.Adapter<WorkoutSetAdapter.ViewHolder>() {

    private val sets = mutableListOf<WorkoutSetUi>()

    inner class ViewHolder(
        private val binding: ViewWorkoutSetBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(set: WorkoutSetUi) {

            binding.txtSet.text = "Serie ${bindingAdapterPosition + 1}"

            binding.edtWeight.setText(
                if (set.weight == 0f) "" else set.weight.toString()
            )

            binding.edtReps.setText(
                if (set.reps == 0) "" else set.reps.toString()
            )

            binding.edtWeight.doAfterTextChanged {

                val position = bindingAdapterPosition

                if (position != RecyclerView.NO_POSITION) {

                    sets[position] = sets[position].copy(

                        weight = it.toString()
                            .toFloatOrNull() ?: 0f

                    )

                }

            }

            binding.edtReps.doAfterTextChanged {

                val position = bindingAdapterPosition

                if (position != RecyclerView.NO_POSITION) {

                    sets[position] = sets[position].copy(

                        reps = it.toString()
                            .toIntOrNull() ?: 0

                    )

                }

            }

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding = ViewWorkoutSetBinding.inflate(
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

        holder.bind(sets[position])

    }

    override fun getItemCount(): Int = sets.size

    fun updateSets(newSets: List<WorkoutSetUi>) {

        sets.clear()
        sets.addAll(newSets)

        notifyDataSetChanged()

    }

    fun getSets(): List<WorkoutSetUi> {

        return sets.toList()

    }

    fun addSet(set: WorkoutSetUi) {

        sets.add(set)

        notifyItemInserted(sets.lastIndex)

    }

    fun removeSet(position: Int) {

        if (position !in sets.indices) return

        sets.removeAt(position)

        notifyItemRemoved(position)

        notifyItemRangeChanged(position, sets.size)

    }

}

