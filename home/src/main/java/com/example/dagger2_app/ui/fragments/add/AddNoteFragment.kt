package com.example.dagger2_app.ui.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.dagger2_app.di.MyApplication
import com.example.dagger2_app.models.NoteDTO

import com.example.dagger2_app.resource.DBResult
import com.example.home.databinding.FragmentAddBinding
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class AddNoteFragment : Fragment() {
    private lateinit var binding:FragmentAddBinding
    @Inject
    lateinit var viewModel: AddNoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater)
        (requireActivity().application as MyApplication).daggerAppComponent.inject(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.insertion.observe(viewLifecycleOwner){
            when(it){
                is DBResult.Error -> Snackbar.make(requireView(),it.message, Snackbar.LENGTH_SHORT).show()
                is DBResult.Success -> findNavController().popBackStack()
            }
        }

        binding.btnSubmit.setOnClickListener {
            val note = NoteDTO(0,binding.etTitle.text.toString(), binding.etDescription.text.toString())
            viewModel.addNote(note)
        }



    }


}