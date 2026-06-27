package com.example.gymtrackergod.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrackergod.databinding.ViewWorkoutExerciseCardBinding
import com.example.gymtrackergod.databinding.ViewWorkoutSetBinding

import com.example.gymtrackergod.ui.model.WorkoutSetUi

class WorkoutSetAdapter(

    private val sets: MutableList<WorkoutSetUi>,

    private val onDelete: (Int) -> Unit

) : RecyclerView.Adapter<WorkoutSetAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: ViewWorkoutSetBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            workoutSet: WorkoutSetUi,
            position: Int
        ) {

            binding.txtSet.text =
                "Serie ${position + 1}"

            binding.edtWeight.setText(
                workoutSet.weight.toString()
            )

            binding.edtReps.setText(
                workoutSet.reps.toString()
            )

            binding.btnDelete.setOnClickListener {

                onDelete(position)

            }

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding =
            ViewWorkoutSetBinding.inflate(
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

        holder.bind(
            sets[position],
            position
        )

    }

    override fun getItemCount() =
        sets.size

}