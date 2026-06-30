package com.example.gymtrackergod.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gymtrackergod.data.`1`.repository.WorkoutRepository

class WorkoutViewModelFactory(

    private val repository: WorkoutRepository

) : ViewModelProvider.Factory {

}