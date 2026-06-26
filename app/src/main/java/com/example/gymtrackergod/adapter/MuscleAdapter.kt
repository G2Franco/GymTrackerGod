package com.example.gymtrackergod.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrackergod.databinding.ItemMuscleBinding
import com.example.gymtrackergod.ui.model.Muscle

class MuscleAdapter(
    private val onClick: (Muscle) -> Unit
) : ListAdapter<Muscle, MuscleAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(
        private val binding: ItemMuscleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(muscle: Muscle) {

            binding.txtName.text = muscle.name

            binding.root.isChecked = muscle.selected

            binding.root.setOnClickListener {

                onClick(muscle)

            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding = ItemMuscleBinding.inflate(
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

    class DiffCallback : DiffUtil.ItemCallback<Muscle>() {

        override fun areItemsTheSame(
            oldItem: Muscle,
            newItem: Muscle
        ): Boolean {

            return oldItem.name == newItem.name

        }

        override fun areContentsTheSame(
            oldItem: Muscle,
            newItem: Muscle
        ): Boolean {

            return oldItem == newItem

        }

    }

}