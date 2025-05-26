package com.example.profile


import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope

import com.bumptech.glide.Glide
import com.example.profile.data.ProfileInjection
import com.example.profile.databinding.ActivityProfileBinding
import com.example.profile.ui.ProfileIntent

import com.example.profile.ui.ProfileViewModel
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: ProfileViewModel
    private val selectImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            viewModel.onIntent(ProfileIntent.OnChangeProfileUri(it.toString()))
            binding.profileImageView.setImageURI(it)
        }
    }

    private val appNavigator = AppNavigator(this,R.id.main)
    private lateinit var profileNavigator: ProfileNavigator


    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        profileNavigator =  application as ProfileNavigator
        profileNavigator.injectNavigator(appNavigator)
       (application as ProfileInjection).inject(this)
        enableEdgeToEdge()
        setContentView(binding.root)


        lifecycleScope.launch {
            viewModel.state.collect { state->
                if(state.insertion){
                    val snackbar = Snackbar.make(binding.root,"Successfully changed", Snackbar.LENGTH_SHORT)
                    snackbar.view.setBackgroundColor(ContextCompat.getColor(this@ProfileActivity, R.color.green))
                    snackbar.setBackgroundTint(resources.getColor(R.color.green))
                    snackbar.show()
                }

                if(state.profileUri.isEmpty()){
                    binding.profileImageView.setImageResource(R.drawable.profile_placeholder)
                }
                else{
                    Glide.with(this@ProfileActivity)
                        .load(state.profileUri)
                        .into(binding.profileImageView)
                }

                if(state.username.isNotEmpty()){
                    binding.editName.setText(state.username)
                }

            }


        }

        binding.btnBack.setOnClickListener {
            profileNavigator.navigateBackToActivity()
        }

        binding.profileImageContainer.setOnClickListener {
            selectImageLauncher.launch("image/*")
        }



        binding.saveButton.setOnClickListener {
            viewModel.onIntent(ProfileIntent.OnChangeName(binding.editName.text.toString()))

        }

        viewModel.getName()
        viewModel.getImgUri()


    }


}