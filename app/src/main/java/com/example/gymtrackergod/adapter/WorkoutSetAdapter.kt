package com.example.gymtrackergod.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrackergod.data.`1`.entity.WorkoutSet
import com.example.gymtrackergod.databinding.ItemSetBinding

class WorkoutSetAdapter(
    private val sets: List<WorkoutSet>,
    private val onDelete: (WorkoutSet) -> Unit
) : RecyclerView.Adapter<WorkoutSetAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: ItemSetBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(set: WorkoutSet, position: Int) {

            val sdf =
                java.text.SimpleDateFormat(
                    "dd/MM/yyyy",
                    java.util.Locale.getDefault()
                )

            val date =
                sdf.format(
                    java.util.Date(set.date)
                )

            binding.txtSet.text =
                """
    📅 $date
    
    Serie ${position + 1}
    ${set.weight} kg x ${set.reps}
    """.trimIndent()
            binding.root.setOnLongClickListener {

                onDelete(set)

                true
            }

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding =
            ItemSetBinding.inflate(
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
        holder.bind(sets[position], position)

    }

    override fun getItemCount() = sets.size
}