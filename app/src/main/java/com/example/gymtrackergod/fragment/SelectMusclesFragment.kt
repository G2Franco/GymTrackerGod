package com.example.gymtrackergod.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gymtrackergod.R
import com.example.gymtrackergod.adapter.MuscleAdapter
import com.example.gymtrackergod.databinding.FragmentSelectMusclesBinding
import com.example.gymtrackergod.utils.MuscleProvider
import com.example.gymtrackergod.viewmodel.WorkoutViewModel


class SelectMusclesFragment : Fragment() {

    private var _binding: FragmentSelectMusclesBinding? = null
    private val binding get() = _binding!!
    private lateinit var workoutViewModel: WorkoutViewModel
    private lateinit var adapter: MuscleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSelectMusclesBinding.inflate(
            inflater,
            container,
            false
        )
        workoutViewModel =
            ViewModelProvider(requireActivity())[WorkoutViewModel::class.java]
        adapter = MuscleAdapter { muscle ->

            workoutViewModel.toggleMuscle(muscle)

        }
        binding.rvMuscles.layoutManager =
            GridLayoutManager(requireContext(), 2)

        binding.rvMuscles.adapter = adapter

        adapter.submitList(
            MuscleProvider.getMuscles()
        )
        workoutViewModel.muscles.observe(viewLifecycleOwner) { muscles ->

            adapter.submitList(muscles)

            binding.btnContinue.isEnabled =
                muscles.any { it.selected }

        }
        binding.btnContinue.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    SelectExercisesFragment()
                )
                .addToBackStack(null)
                .commit()

        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}