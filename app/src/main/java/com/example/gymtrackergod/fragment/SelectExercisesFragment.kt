package com.example.gymtrackergod.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymtrackergod.adapter.ExerciseSelectionAdapter
import com.example.gymtrackergod.data.`1`.database.DatabaseProvider
import com.example.gymtrackergod.data.`1`.repository.ExerciseRepository
import com.example.gymtrackergod.databinding.FragmentSelectExercisesBinding
import com.example.gymtrackergod.viewmodel.WorkoutViewModel
import kotlinx.coroutines.launch

class SelectExercisesFragment : Fragment() {

    private var _binding: FragmentSelectExercisesBinding? = null
    private val binding get() = _binding!!

    private lateinit var workoutViewModel: WorkoutViewModel
    private lateinit var repository: ExerciseRepository
    private lateinit var adapter: ExerciseSelectionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSelectExercisesBinding.inflate(
            inflater,
            container,
            false
        )

        workoutViewModel =
            ViewModelProvider(requireActivity())[WorkoutViewModel::class.java]

        val database = DatabaseProvider.getDatabase(requireContext())

        adapter = ExerciseSelectionAdapter { exercise ->

            workoutViewModel.toggleExercise(
                exercise.exercise.id
            )

        }

        binding.rvExercises.layoutManager =
            LinearLayoutManager(requireContext())

        binding.rvExercises.adapter = adapter

        repository = ExerciseRepository(
            database.exerciseDao()
        )

        val muscles =
            workoutViewModel
                .getSelectedMuscles()
                .map { it.name }

        viewLifecycleOwner.lifecycleScope.launch {

            val exercises =
                repository.getExercisesByMuscles(muscles)

            workoutViewModel.setExercises(exercises)

        }
        workoutViewModel.exerciseSelections.observe(
            viewLifecycleOwner
        ) {

            adapter.submitList(it)

        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}