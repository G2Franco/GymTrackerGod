package com.example.gymtrackergod.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymtrackergod.adapter.WorkoutAdapter
import com.example.gymtrackergod.data.`1`.database.AppDatabase
import com.example.gymtrackergod.data.`1`.database.DatabaseProvider
import com.example.gymtrackergod.data.`1`.repository.WorkoutRepository
import com.example.gymtrackergod.databinding.FragmentWorkoutBinding
import com.example.gymtrackergod.viewmodel.WorkoutViewModel
import com.google.android.material.snackbar.Snackbar


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

        _binding = FragmentWorkoutBinding.inflate(
            inflater,
            container,
            false
        )

        workoutViewModel =
            ViewModelProvider(requireActivity())[WorkoutViewModel::class.java]


        val db = DatabaseProvider.getDatabase(requireContext())

        val repository = WorkoutRepository(
            db.workoutSessionDao(),
            db.workoutSetDao()
        )

        workoutViewModel.setRepository(repository)



        adapter = WorkoutAdapter(

            onAddSet = { exercise, sets ->

                workoutViewModel.addSet(

                    exercise.id,

                    sets

                )

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

                Snackbar.make(

                    binding.root,

                    "✅ ${exercise.name} guardado",

                    Snackbar.LENGTH_SHORT

                ).show()

            }

        )

        binding.rvWorkout.layoutManager =
            LinearLayoutManager(requireContext())

        binding.rvWorkout.adapter = adapter

        binding.btnFinishWorkout.isEnabled = false
        workoutViewModel.workoutExercises.observe(viewLifecycleOwner) { workout ->

            adapter.submitList(workout)
            binding.btnFinishWorkout.isEnabled = workoutViewModel.canFinishWorkout()

        }
        workoutViewModel.canFinishWorkoutState.observe(viewLifecycleOwner) { enabled ->

            binding.btnFinishWorkout.isEnabled = enabled

        }

        workoutViewModel.progress.observe(viewLifecycleOwner) { progress ->

            binding.progressWorkout.progress = progress

            binding.txtProgress.text = "$progress% completado"

        }

        workoutViewModel.workoutExercises.observe(viewLifecycleOwner) { workout ->

            adapter.submitList(workout)

            binding.btnFinishWorkout.isEnabled =
                workoutViewModel.canFinishWorkout()

        }



        binding.btnFinishWorkout.setOnClickListener {

            workoutViewModel.finishWorkout()

            Snackbar.make(

                binding.root,

                "🎉 Entrenamiento guardado correctamente",

                Snackbar.LENGTH_LONG

            ).show()

        }



        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
