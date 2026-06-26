package com.example.gymtrackergod.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gymtrackergod.databinding.FragmentSelectExercisesBinding
import com.example.gymtrackergod.viewmodel.WorkoutViewModel

class SelectExercisesFragment : Fragment() {

    private var _binding: FragmentSelectExercisesBinding? = null
    private val binding get() = _binding!!

    private lateinit var workoutViewModel: WorkoutViewModel

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

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}