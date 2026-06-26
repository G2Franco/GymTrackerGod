package com.example.gymtrackergod.ui.model

data class HistoryItem(
    val date: String,
    val maxWeight: Float,
    val volume: Float,
    val totalSets: Int,
    val sets: List<String>
)