package com.example.gymtrackergod.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymtrackergod.R
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
        binding.btnStartWorkout.setOnClickListener {

            try {

                workoutViewModel.startWorkout()
                Toast.makeText(requireContext(), "1. startWorkout OK", Toast.LENGTH_SHORT).show()

                val fragment = WorkoutFragment()
                Toast.makeText(requireContext(), "2. Fragment creado", Toast.LENGTH_SHORT).show()

                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit()

                Toast.makeText(requireContext(), "3. Commit realizado", Toast.LENGTH_SHORT).show()

            } catch (e: Exception) {

                e.printStackTrace()

                Toast.makeText(
                    requireContext(),
                    e.javaClass.simpleName + ": " + e.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}