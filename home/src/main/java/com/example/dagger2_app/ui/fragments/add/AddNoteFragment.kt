package com.example.dagger2_app.ui.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat

import androidx.navigation.fragment.findNavController
import com.example.dagger2_app.HomeNavigator
import com.example.dagger2_app.data.local.Injection
import com.example.dagger2_app.models.NoteDTO
import com.example.home.R

import com.example.home.databinding.FragmentAddBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
        (requireActivity().application as Injection).inject(this)
        setNavigation()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.state.collect {
                if(it.insertion){
                    val snackbar = Snackbar.make(requireView(),"Successfully added", Snackbar.LENGTH_SHORT)
                    snackbar.view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green))
                    snackbar.setBackgroundTint(resources.getColor(R.color.green))
                    snackbar.show()

                    findNavController().popBackStack()
                    viewModel.onIntent(AddNoteIntent.OnClearState)
                }
                if(!it.insertion && it.error.isNotEmpty()){
                    Snackbar.make(requireView(),it.error, Snackbar.LENGTH_SHORT).show()
                }
            }
        }


        binding.btnSubmit.setOnClickListener {
            val note = NoteDTO(0,binding.etTitle.text.toString(), binding.etDescription.text.toString())
            viewModel.onIntent(AddNoteIntent.OnAddNote(note))
        }



    }

    private fun setNavigation(){
        binding.btnBack.setOnClickListener {
//             findNavController().popBackStack()
            (requireActivity().application as HomeNavigator).navigateBackToHomeFragment()
        }
    }


}