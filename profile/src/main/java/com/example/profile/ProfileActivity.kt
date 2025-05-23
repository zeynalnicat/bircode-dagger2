package com.example.profile


import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat

import com.bumptech.glide.Glide
import com.example.profile.data.ProfileInjection
import com.example.profile.databinding.ActivityProfileBinding

import com.example.profile.ui.ProfileViewModel
import javax.inject.Inject

class ProfileActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: ProfileViewModel
    private val selectImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            viewModel.addImgUri(it.toString())
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

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.profileImageContainer.setOnClickListener {
            selectImageLauncher.launch("image/*")
        }

        viewModel.imgUri.observe(this){
            if(it.isEmpty()) binding.profileImageView.setImageResource(R.drawable.profile_placeholder)
            else{
                Glide.with(this)
                    .load(it)
                    .into(binding.profileImageView)
            }
        }

        viewModel.name.observe(this){
            if(it.isNotEmpty()){
                binding.editName.setText(it)
            }

        }

        binding.saveButton.setOnClickListener {
            viewModel.addName(binding.editName.text.toString())

        }

        viewModel.getName()
        viewModel.getImgUri()


    }


}