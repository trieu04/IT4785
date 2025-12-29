package com.example.a11.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a11.R
import com.example.a11.StudentAdapter
import com.example.a11.StudentViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentListFragment : Fragment() {

    private val viewModel: StudentViewModel by activityViewModels()
    private lateinit var adapter: StudentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvStudents = view.findViewById<RecyclerView>(R.id.rvStudents)
        val fabAddStudent = view.findViewById<FloatingActionButton>(R.id.fabAddStudent)

        adapter = StudentAdapter(mutableListOf()) { student, position ->
            val bundle = Bundle().apply {
                putParcelable("student", student)
                putInt("position", position)
            }
            findNavController().navigate(R.id.action_studentListFragment_to_updateStudentFragment, bundle)
        }
        rvStudents.layoutManager = LinearLayoutManager(requireContext())
        rvStudents.adapter = adapter

        viewModel.students.observe(viewLifecycleOwner) { students ->
            adapter.updateData(students)
        }

        fabAddStudent.setOnClickListener {
            findNavController().navigate(R.id.action_studentListFragment_to_addStudentFragment)
        }
    }
}
