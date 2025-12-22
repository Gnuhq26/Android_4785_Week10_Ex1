package com.example.android_week10_ex1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StudentListFragment : Fragment() {

    private val viewModel: StudentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_list, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = StudentAdapter(
            students = mutableListOf(),
            onDelete = { position ->
                viewModel.deleteStudent(position)
            },
            onSelect = { student ->
                val action = StudentListFragmentDirections.actionStudentListToUpdateStudent(student.id, student.name)
                findNavController().navigate(action)
            }
        )

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.students.observe(viewLifecycleOwner) { students ->
            adapter.updateList(students ?: mutableListOf())
        }

        // Add button to navigate to AddStudentFragment
        view.findViewById<View>(R.id.btn_add).setOnClickListener {
            findNavController().navigate(R.id.action_studentList_to_addStudent)
        }

        return view
    }
}
