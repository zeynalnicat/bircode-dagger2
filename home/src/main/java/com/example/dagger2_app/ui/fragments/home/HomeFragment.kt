package com.example.dagger2_app.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dagger2_app.data.local.Injection

import com.example.dagger2_app.models.NoteDTO
import com.example.dagger2_app.resource.DBResult
import com.example.dagger2_app.ui.adapters.NotesAdapter
import com.example.home.R
import com.example.home.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding


    @Inject
    lateinit var adapter : NotesAdapter
    @Inject
    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        (requireActivity().application as Injection).inject(this)
        setNavigation()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.notes.observe(viewLifecycleOwner){
            when(it){
                is DBResult.Error -> {
                    Snackbar.make(requireView(),it.message,Snackbar.LENGTH_SHORT).show()
                }
                is DBResult.Success -> {
                    if(it.data.isNotEmpty()){
                        binding.txtNotes.visibility = View.GONE
                        setAdapter(it.data)
                    }else{
                        binding.txtNotes.visibility = View.VISIBLE
                    }

                }
//                DBResult.Loading -> TODO()
            }
        }


        homeViewModel.getNotes()



    }

    private fun setAdapter(notes:List<NoteDTO>){
        adapter.setList(notes)
        binding.rvNotes.layoutManager = GridLayoutManager(requireContext(),1)
        binding.rvNotes.adapter = adapter
    }

    private fun setNavigation(){
        binding.fbAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)
        }

        binding.btnBack.setOnClickListener{
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }


}