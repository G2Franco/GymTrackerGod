package com.example.gymtrackergod.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymtrackergod.data.`1`.entity.Exercise
import com.example.gymtrackergod.ui.model.ExerciseSelection
import com.example.gymtrackergod.ui.model.Muscle
import com.example.gymtrackergod.utils.MuscleProvider

class WorkoutViewModel : ViewModel() {

    private val _muscles =
        MutableLiveData(MuscleProvider.getMuscles())

    val muscles: LiveData<List<Muscle>>
        get() = _muscles

    private val _selectedExercises =
        MutableLiveData<List<Exercise>>(emptyList())

    val selectedExercises: LiveData<List<Exercise>>
        get() = _selectedExercises

    fun toggleMuscle(muscle: Muscle) {

        val updated = _muscles.value?.map {

            if (it.name == muscle.name) {

                it.copy(
                    selected = !it.selected
                )

            } else {

                it

            }

        }

        _muscles.value = updated
    }

    fun getSelectedMuscles(): List<Muscle> {

        return _muscles.value
            ?.filter { it.selected }
            ?: emptyList()

    }
    private val _exerciseSelections =
        MutableLiveData<List<ExerciseSelection>>(emptyList())

    val exerciseSelections: LiveData<List<ExerciseSelection>>
        get() = _exerciseSelections

    fun setExercises(exercises: List<Exercise>) {

        _exerciseSelections.value =
            exercises.map {

                ExerciseSelection(it)

            }

    }
    fun toggleExercise(exerciseId: Int) {

        _exerciseSelections.value =
            _exerciseSelections.value?.map {

                if (it.exercise.id == exerciseId) {

                    it.copy(
                        selected = !it.selected
                    )

                } else {

                    it

                }

            }

    }

}