package com.example.gymtrackergod.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymtrackergod.data.`1`.entity.Exercise
import com.example.gymtrackergod.ui.model.ExerciseSelection
import com.example.gymtrackergod.ui.model.Muscle
import com.example.gymtrackergod.ui.model.WorkoutExercise
import com.example.gymtrackergod.ui.model.WorkoutSetUi
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
    fun getSelectedExercises(): List<Exercise> {

        return _exerciseSelections.value
            ?.filter { it.selected }
            ?.map { it.exercise }
            ?: emptyList()

    }
    fun canStartWorkout(): Boolean {

        return _exerciseSelections.value
            ?.any { it.selected }
            ?: false

    }
    private val _workoutExercises =
        MutableLiveData<List<WorkoutExercise>>()

    val workoutExercises: LiveData<List<WorkoutExercise>>
        get() = _workoutExercises

    fun startWorkout() {

        val exercises = getSelectedExercises()

        _workoutExercises.value = exercises.map {

            WorkoutExercise(

                exercise = it,

                sets = mutableListOf(
                    WorkoutSetUi()
                )

            )

        }

    }
    fun addSet(exerciseId: Int) {

        val updated = _workoutExercises.value?.map { workout ->

            if (workout.exercise.id == exerciseId) {

                val newSets = workout.sets.toMutableList()

                newSets.add(
                    WorkoutSetUi()
                )

                workout.copy(
                    sets = newSets
                )

            } else {

                workout

            }

        }

        _workoutExercises.value = updated

    }
    fun removeSet(
        exerciseId: Int,
        index: Int
    ) {

        val updated = _workoutExercises.value?.map { workout ->

            if (workout.exercise.id == exerciseId) {

                val newSets =
                    workout.sets.toMutableList()

                if (index in newSets.indices) {

                    newSets.removeAt(index)

                }

                workout.copy(
                    sets = newSets
                )

            } else {

                workout

            }

        }

        _workoutExercises.value = updated

    }
    fun completeExercise(
        exerciseId: Int
    ) {

        _workoutExercises.value =
            _workoutExercises.value?.map {

                if (it.exercise.id == exerciseId) {

                    it.copy(
                        saved = true
                    )

                } else {

                    it

                }

            }

    }

    fun getProgress(): Int {

        val exercises =
            _workoutExercises.value ?: return 0

        if (exercises.isEmpty()) return 0

        val completed =
            exercises.count { it.saved }

        return completed * 100 / exercises.size

    }
    fun saveSet(
        exerciseId: Int,
        index: Int,
        weight: Float,
        reps: Int
    ) {

        val updated = _workoutExercises.value?.map { workout ->

            if (workout.exercise.id == exerciseId) {

                val sets = workout.sets.toMutableList()

                if (index in sets.indices) {

                    sets[index] = sets[index].copy(
                        weight = weight,
                        reps = reps,
                        saved = true
                    )

                }

                workout.copy(
                    sets = sets
                )

            } else {

                workout

            }

        }

        _workoutExercises.value = updated

    }
    fun saveExercise(
        exerciseId: Int,
        sets: List<WorkoutSetUi>
    ) {

        _workoutExercises.value =
            _workoutExercises.value?.map { workout ->

                if (workout.exercise.id == exerciseId) {

                    workout.copy(

                        sets = sets.toMutableList(),

                        saved = true

                    )

                } else {

                    workout

                }

            }

    }


    fun canFinishWorkout(): Boolean {

        val exercises =
            _workoutExercises.value ?: return false

        return exercises.isNotEmpty() &&
                exercises.all { it.saved }

    }





}