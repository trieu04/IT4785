package com.example.a11.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.a11.Student
import com.example.a11.StudentViewModel
import com.example.a11.databinding.FragmentUpdateStudentBinding

class UpdateStudentFragment : Fragment() {

    private val viewModel: StudentViewModel by activityViewModels()
    private lateinit var binding: FragmentUpdateStudentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateStudentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentStudent = arguments?.getParcelable<Student>("student")
        val position = arguments?.getInt("position", -1) ?: -1

        // We bind a COPY or the object itself. 
        // Typically passed by Parcelable is a copy (new instance).
        // So we can bind it directly.
        binding.student = currentStudent

        binding.btnUpdate.setOnClickListener {
            binding.student?.let { updatedStudent ->
                if (position != -1) {
                    viewModel.updateStudent(position, updatedStudent)
                }
            }
            findNavController().popBackStack()
        }
    }
}
