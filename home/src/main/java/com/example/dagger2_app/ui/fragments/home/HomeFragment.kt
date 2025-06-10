package com.example.dagger2_app.ui.fragments.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope

import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.di.MyApplication
import com.example.dagger2_app.di.DaggerAppComponent
import com.example.dagger2_app.di.HomeAppModule
import com.example.dagger2_app.di.HomeViewModelModule

import com.example.dagger2_app.models.NoteDTO
import com.example.dagger2_app.ui.adapters.NotesAdapter
import com.example.home.R
import com.example.home.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeFragment : Fragment(), NotesAdapter.ICallback {
    private lateinit var binding: FragmentHomeBinding


    @Inject
    lateinit var adapter: NotesAdapter

    @Inject
    lateinit var homeViewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        val coreComponent = (requireActivity().application as MyApplication).appComponent
        val appComponent = DaggerAppComponent.builder().coreComponent(coreComponent).homeAppModule(
            HomeAppModule(requireContext())
        ).homeViewModelModule(HomeViewModelModule()).build()

        appComponent.inject(this)



        return binding.root
    }


    private fun setupOnBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    homeViewModel.onIntent(HomeIntent.OnFinishChain)
                }
            }
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigation()
        setupOnBackPress()
        handleStateListener()
        handleOnFetchNotes()


    }


    private fun handleOnFetchNotes() {
        homeViewModel.onIntent(HomeIntent.OnGetDto)
    }

    private fun handleStateListener() {
        lifecycleScope.launch {
            homeViewModel.state.collect {
                setAdapter(it.notes)
                binding.txtNotes.isVisible = it.notes.isEmpty()

            }

        }

        lifecycleScope.launch {
            homeViewModel.effect.collect { effect ->
                when (effect) {
                    is HomeUiEffect.ShowSnackbar -> {
                        val snackbar =
                            Snackbar.make(requireView(), effect.message, Snackbar.LENGTH_SHORT)
                        snackbar.view.setBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.red
                            )
                        )
                        snackbar.setBackgroundTint(resources.getColor(R.color.red))
                        snackbar.show()
                    }
                }
            }
        }
    }


    private fun setAdapter(notes: List<NoteDTO>) {
        adapter.setList(notes)
        adapter.setCallBack(this)
        binding.rvNotes.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.rvNotes.adapter = adapter
    }

    private fun setNavigation() {
        binding.fbAdd.setOnClickListener {
            homeViewModel.onIntent(HomeIntent.OnNavigateToAddNoteFragment())
        }

        binding.btnBack.setOnClickListener {
            homeViewModel.onIntent(HomeIntent.OnFinishChain)

        }

    }

    override fun remove(noteDTO: NoteDTO) {
        homeViewModel.onIntent(HomeIntent.OnRemoveNote(noteDTO))
    }

    override fun click(noteDTO: NoteDTO) {
        homeViewModel.onIntent(
            HomeIntent.OnNavigateToAddNoteFragment(
                noteDTO.title,
                noteDTO.description
            )
        )
    }


}