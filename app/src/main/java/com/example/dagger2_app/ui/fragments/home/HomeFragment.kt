package com.example.dagger2_app.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dagger2_app.databinding.FragmentHomeBinding
import com.example.dagger2_app.models.NoteDTO
import com.example.dagger2_app.resource.DBResult
import com.example.dagger2_app.ui.adapters.NotesAdapter
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private var adapter : NotesAdapter? = null
    @Inject
    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        adapter = NotesAdapter()
        binding.rvNotes.layoutManager = GridLayoutManager(requireContext(),1)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.notes.observe(viewLifecycleOwner){
            when(it){
                is DBResult.Error -> {
                    Snackbar.make(requireView(),it.message,Snackbar.LENGTH_SHORT).show()
                }
                is DBResult.Success -> {setAdapter(it.data)}
//                DBResult.Loading -> TODO()
            }
        }

    }

    private fun setAdapter(notes:List<NoteDTO>){
        adapter?.setList(notes)
    }


}