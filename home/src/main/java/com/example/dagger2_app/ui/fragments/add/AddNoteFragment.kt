package com.example.dagger2_app.ui.fragments.add

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope


import com.example.core.constants.AppKeys.ARG_DESCRIPTION
import com.example.core.constants.AppKeys.ARG_TITLE
import com.example.core.constants.AppStrings
import com.example.core.di.MyApplication
import com.example.core.extensions.setTextIfChanged
import com.example.dagger2_app.HomeActivity
import com.example.dagger2_app.HomeNavigator
import com.example.dagger2_app.MiddleActivity
import com.example.dagger2_app.di.DaggerAppComponent
import com.example.dagger2_app.di.HomeAppModule
import com.example.dagger2_app.di.HomeViewModelModule
import com.example.home.R

import com.example.home.databinding.FragmentAddBinding
import com.github.terrakok.cicerone.Router
import com.google.android.material.snackbar.Snackbar

import kotlinx.coroutines.launch
import javax.inject.Inject

class AddNoteFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding

    @Inject
    lateinit var viewModel: AddNoteViewModel
    private val channelID = "banking"
    private var notificationManager: NotificationManager? = null
    companion object {

        fun newInstance(title: String, description: String): AddNoteFragment {
            val fragment = AddNoteFragment()
            fragment.arguments = Bundle().apply {
                putString(ARG_TITLE,title)
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
            HomeAppModule(requireContext())
        ).homeViewModelModule(HomeViewModelModule()).build()

        appComponent.inject(this)
        notificationManager = requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotification()
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
    private fun createNotification(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelID, AppStrings.appTitle,importance).apply {
                description = AppStrings.successfulOperation
            }
            notificationManager?.createNotificationChannel(channel)

        }
    }

    private fun displayNotification(){

        val notificationId= 45
        val notification : Notification = NotificationCompat.Builder(requireActivity(),channelID)
            .setContentTitle(AppStrings.appTitle)
            .setContentText(AppStrings.successfulInsertionNote)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH).build()
        notificationManager?.notify(notificationId,notification)
    }

    private fun setupEditTextValues() {
        viewModel.onIntent(AddNoteIntent.OnSetTitle(arguments?.getString(ARG_TITLE) ?: ""))
        viewModel.onIntent(
            AddNoteIntent.OnSetDescription(
                arguments?.getString(ARG_DESCRIPTION) ?: ""
            )
        )
    }

    private fun editTextListener() {
        binding.etTitle.doAfterTextChanged {
            val title = it?.toString().orEmpty()
            viewModel.onIntent(AddNoteIntent.OnSetTitle(title))
        }

        binding.etDescription.doAfterTextChanged {
            val desc = it?.toString().orEmpty()
            viewModel.onIntent(AddNoteIntent.OnSetDescription(desc))
        }

    }

    private fun handleSubmit() {
        binding.btnSubmit.setOnClickListener {
            viewModel.onIntent(AddNoteIntent.OnAddNote)
        }
    }


    private fun handleStateListener() {
        lifecycleScope.launch {
            viewModel.state.collect {
                binding.etTitle.setTextIfChanged(it.title)
                binding.etDescription.setTextIfChanged(it.description)
            }
        }

        lifecycleScope.launch {
            viewModel.effect.collect { effect ->
                when (effect) {
                    AddNoteUiEffect.NotifyInsertion -> {
                        val snackbar = Snackbar.make(
                            requireView(),
                            AppStrings.successfulOperation,
                            Snackbar.LENGTH_SHORT
                        )
                        snackbar.view.setBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.green
                            )
                        )
                        snackbar.setBackgroundTint(resources.getColor(R.color.green))
                        snackbar.show()
                        displayNotification()
                        viewModel.onIntent(AddNoteIntent.OnNavigateBack)
                    }

                    is AddNoteUiEffect.ShowSnackbar -> {
                        Snackbar.make(requireView(), effect.message, Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setNavigation() {
        binding.btnBack.setOnClickListener {
            viewModel.onIntent(AddNoteIntent.OnNavigateBack)
        }
    }


}