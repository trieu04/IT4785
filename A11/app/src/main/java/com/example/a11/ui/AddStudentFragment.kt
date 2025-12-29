package com.example.a11.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.a11.Student
import com.example.a11.StudentViewModel
import com.example.a11.databinding.FragmentAddStudentBinding

class AddStudentFragment : Fragment() {

    private val viewModel: StudentViewModel by activityViewModels()
    private lateinit var binding: FragmentAddStudentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddStudentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize a new empty student for binding
        val newStudent = Student("", "", "", "")
        binding.student = newStudent

        binding.btnSave.setOnClickListener {
            // Validate input if needed
            viewModel.addStudent(binding.student!!)
            findNavController().popBackStack()
        }
    }
}
