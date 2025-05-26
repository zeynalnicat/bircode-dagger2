package com.example.dagger2_app.ui.fragments.home

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dagger2_app.HomeActivity
import com.example.dagger2_app.HomeNavigator
import com.example.dagger2_app.data.local.Injection

import com.example.dagger2_app.models.NoteDTO
import com.example.dagger2_app.ui.adapters.NotesAdapter
import com.example.home.R
import com.example.home.databinding.FragmentHomeBinding
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

class HomeFragment : Fragment(), NotesAdapter.ICallback {
    private lateinit var binding: FragmentHomeBinding


    @Inject
    lateinit var adapter : NotesAdapter
    @Inject
    lateinit var homeViewModel: HomeViewModel

    private lateinit var homeNavigator: HomeNavigator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        (requireActivity().application as Injection).inject(this)
        homeNavigator = requireActivity().application as HomeNavigator
        setNavigation()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            homeViewModel.state.collect {
                if(it.error.isNotEmpty()){
                    val snackbar = Snackbar.make(requireView(),it.error,Snackbar.LENGTH_SHORT)
                    snackbar.view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))
                    snackbar.setBackgroundTint(resources.getColor(R.color.red))
                    snackbar.show()
                }

                if(it.notes.isNotEmpty()){
                    binding.txtNotes.visibility = View.GONE
                    setAdapter(it.notes)
                }else{
                    binding.txtNotes.visibility = View.VISIBLE
                }
            }

        }

        homeViewModel.onIntent(HomeIntent.OnGetDto)



    }

    private fun setAdapter(notes:List<NoteDTO>){
        adapter.setList(notes)
        adapter.setCallBack(this)
        binding.rvNotes.layoutManager = GridLayoutManager(requireContext(),1)
        binding.rvNotes.adapter = adapter
    }

    private fun setNavigation(){
        binding.fbAdd.setOnClickListener {
//            findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)
            homeNavigator.navigateToAddNotesFragment()
        }

        binding.btnBack.setOnClickListener{
//            val intent = Intent()
//            intent.putExtra("userId", Random.nextInt(0,100))
//            requireActivity().setResult(RESULT_OK,intent)
//            requireActivity().finish()\

            (requireActivity() as HomeActivity).onNavigateBack()




        }
    }

    override fun remove(noteDTO: NoteDTO) {
         homeViewModel.onIntent(HomeIntent.OnRemoveNote(noteDTO))
    }



}