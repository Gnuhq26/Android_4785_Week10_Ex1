package com.example.android_week10_ex1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.android_week10_ex1.databinding.FragmentUpdateStudentBinding

class UpdateStudentFragment : Fragment() {

    private val viewModel: StudentViewModel by activityViewModels()
    private lateinit var binding: FragmentUpdateStudentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateStudentBinding.inflate(inflater, container, false)

        // Get arguments from Bundle
        val studentId = arguments?.getString("studentId") ?: ""
        val studentName = arguments?.getString("studentName") ?: ""

        binding.edtId.setText(studentId)
        binding.edtName.setText(studentName)

        binding.btnUpdate.setOnClickListener {
            val id = binding.edtId.text.toString()
            val name = binding.edtName.text.toString()
            if (id.isNotEmpty() && name.isNotEmpty()) {
                viewModel.updateStudent(StudentModel(id, name))
                findNavController().popBackStack()
            }
        }

        binding.btnCancel.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }
}
