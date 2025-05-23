package com.example.profile


import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope

import com.bumptech.glide.Glide
import com.example.profile.data.ProfileInjection
import com.example.profile.databinding.ActivityProfileBinding
import com.example.profile.ui.ProfileIntent

import com.example.profile.ui.ProfileViewModel
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


    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
       (application as ProfileInjection).inject(this)
        enableEdgeToEdge()
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.m)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }



        lifecycleScope.launch {
            viewModel.state.collect { state->
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
            onBackPressedDispatcher.onBackPressed()
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