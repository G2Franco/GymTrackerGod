package com.example.gymtrackergod.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrackergod.databinding.ItemWorkoutDayBinding
import com.example.gymtrackergod.ui.model.WorkoutDay

class WorkoutDayAdapter(
    private val workoutDays: List<WorkoutDay>,
    private val onClick: (WorkoutDay) -> Unit
) : RecyclerView.Adapter<WorkoutDayAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: ItemWorkoutDayBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(day: WorkoutDay) {
            binding.txtDay.text = "${day.icon} ${day.name}"
            binding.root.setOnClickListener { onClick(day) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding = ItemWorkoutDayBinding.inflate(
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
        holder.bind(workoutDays[position])
    }

    override fun getItemCount(): Int = workoutDays.size
}