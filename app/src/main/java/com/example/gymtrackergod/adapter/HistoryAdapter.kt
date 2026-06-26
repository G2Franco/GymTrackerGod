package com.example.gymtrackergod.adapter
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtrackergod.databinding.ItemHistoryBinding
import com.example.gymtrackergod.ui.model.HistoryItem

class HistoryAdapter(
    private val items: List<HistoryItem>
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: ItemHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: HistoryItem) {

            binding.txtDate.text =
                "📅 ${item.date}"

            binding.txtStats.text =
                """
        🏆 Peso máximo: ${item.maxWeight} kg
        
        📊 Volumen: ${item.volume} kg
        
        🔢 Series: ${item.totalSets}
        """.trimIndent()

            binding.txtSets.text =
                item.sets.joinToString("\n")
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding =
            ItemHistoryBinding.inflate(
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
        holder.bind(items[position])
    }

    override fun getItemCount() =
        items.size
}