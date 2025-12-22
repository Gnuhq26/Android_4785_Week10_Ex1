package com.example.android_week10_ex1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android_week10_ex1.databinding.FragmentUpdateStudentBinding

class UpdateStudentFragment : Fragment() {

    private val viewModel: StudentViewModel by activityViewModels()
    private lateinit var binding: FragmentUpdateStudentBinding
    private val args: UpdateStudentFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateStudentBinding.inflate(inflater, container, false)

        binding.edtId.setText(args.studentId)
        binding.edtName.setText(args.studentName)

        val position = viewModel.students.value?.indexOfFirst { it.id == args.studentId } ?: -1

        binding.btnUpdate.setOnClickListener {
            val id = binding.edtId.text.toString()
            val name = binding.edtName.text.toString()
            if (id.isNotEmpty() && name.isNotEmpty() && position >= 0) {
                viewModel.updateStudent(position, StudentModel(id, name))
                findNavController().popBackStack()
            }
        }

        binding.btnCancel.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }
}
