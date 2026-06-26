package com.example.gymtrackergod.ui.model

sealed class ExerciseListItem {

    data class Header(
        val title: String
    ) : ExerciseListItem()

    data class Item(
        val id: Int,
        val name: String,
        val muscle: String
    ) : ExerciseListItem()
}