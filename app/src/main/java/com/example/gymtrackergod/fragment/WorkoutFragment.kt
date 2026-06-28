package com.example.gymtrackergod.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymtrackergod.adapter.WorkoutAdapter
import com.example.gymtrackergod.databinding.FragmentWorkoutBinding
import com.example.gymtrackergod.viewmodel.WorkoutViewModel

class WorkoutFragment : Fragment() {

    private var _binding: FragmentWorkoutBinding? = null
    private val binding get() = _binding!!

    private lateinit var workoutViewModel: WorkoutViewModel

    private lateinit var adapter: WorkoutAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding =
            FragmentWorkoutBinding.inflate(
                inflater,
                container,
                false
            )

        workoutViewModel =
            ViewModelProvider(requireActivity())[WorkoutViewModel::class.java]

        binding.rvWorkout.layoutManager =
            LinearLayoutManager(requireContext())
        workoutViewModel.workoutExercises.observe(viewLifecycleOwner) { workout ->

            adapter = WorkoutAdapter(

                exercises = workout,

                onAddSet = { exercise ->

                    workoutViewModel.addSet(exercise.id)

                },

                onDeleteSet = { exercise, index ->

                    workoutViewModel.removeSet(
                        exercise.id,
                        index
                    )

                },

                onSaveExercise = { exercise, sets ->

                    workoutViewModel.saveExercise(
                        exercise.id,
                        sets
                    )

                }

            )

            binding.rvWorkout.adapter = adapter

        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}