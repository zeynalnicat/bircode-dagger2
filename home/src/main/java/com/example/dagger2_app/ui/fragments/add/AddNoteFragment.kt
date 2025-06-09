package com.example.dagger2_app.ui.fragments.add

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope

import androidx.navigation.fragment.findNavController
import com.example.core.di.MyApplication
import com.example.core.extensions.setTextIfChanged
import com.example.dagger2_app.HomeNavigator
import com.example.dagger2_app.data.local.Injection
import com.example.dagger2_app.di.DaggerAppComponent
import com.example.dagger2_app.di.HomeAppModule
import com.example.dagger2_app.di.HomeViewModelModule
import com.example.dagger2_app.models.NoteDTO
import com.example.home.R

import com.example.home.databinding.FragmentAddBinding
import com.github.terrakok.cicerone.Router
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddNoteFragment : Fragment() {
    private lateinit var binding:FragmentAddBinding
    @Inject
    lateinit var viewModel: AddNoteViewModel

    companion object {
        private const val ARG_TITLE = "arg_title"
        private const val ARG_DESCRIPTION = "arg_description"

        fun newInstance(title: String, description: String): AddNoteFragment {
            val fragment = AddNoteFragment()
            fragment.arguments = Bundle().apply {
                putString(ARG_TITLE, title)
                putString(ARG_DESCRIPTION, description)
            }
            return fragment
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater)
        val coreComponent = (requireActivity().application as MyApplication).appComponent
        val appComponent = DaggerAppComponent.builder().coreComponent(coreComponent).homeAppModule(
            HomeAppModule(requireContext())).homeViewModelModule(HomeViewModelModule()).build()

        appComponent.inject(this)
        setNavigation()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEditTextValues()
        handleStateListener()

        editTextListener()
        handleSubmit()


    }

    private fun setupEditTextValues(){
        viewModel.onIntent(AddNoteIntent.OnSetTitle(arguments?.getString(ARG_TITLE) ?: ""))
        viewModel.onIntent(AddNoteIntent.OnSetDescription(arguments?.getString(ARG_DESCRIPTION) ?: ""))
    }

    private fun editTextListener(){
        binding.etTitle.doAfterTextChanged {
            val title = it?.toString().orEmpty()
            viewModel.onIntent(AddNoteIntent.OnSetTitle(title))
        }

        binding.etDescription.doAfterTextChanged {
            val desc = it?.toString().orEmpty()
            viewModel.onIntent(AddNoteIntent.OnSetDescription(desc))
        }

    }

    private fun handleSubmit(){
        binding.btnSubmit.setOnClickListener {
            viewModel.onIntent(AddNoteIntent.OnAddNote)
        }
    }


    private fun handleStateListener(){
        lifecycleScope.launch {
            viewModel.state.collect {
                binding.etTitle.setTextIfChanged(it.title)
                binding.etDescription.setTextIfChanged(it.description)
            }
        }

        lifecycleScope.launch {
            viewModel.effect.collect {effect->
                when(effect){
                    AddNoteUiEffect.NotifyInsertion -> {
                        val snackbar = Snackbar.make(requireView(),"Successfully added", Snackbar.LENGTH_SHORT)
                        snackbar.view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green))
                        snackbar.setBackgroundTint(resources.getColor(R.color.green))
                        snackbar.show()

                        viewModel.onIntent(AddNoteIntent.OnNavigateBack)}
                    is AddNoteUiEffect.ShowSnackbar ->{
                        Snackbar.make(requireView(),effect.message, Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setNavigation(){
        binding.btnBack.setOnClickListener {
            viewModel.onIntent(AddNoteIntent.OnNavigateBack)
        }
    }


}