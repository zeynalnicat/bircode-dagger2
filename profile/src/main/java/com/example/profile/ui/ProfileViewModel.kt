package com.example.profile.ui


import androidx.lifecycle.ViewModel
import com.example.profile.data.local.SharedPreferenceHelper
import com.example.profile.ui.ProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val sharedPreferenceHelper: SharedPreferenceHelper): ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    fun onIntent(intent: ProfileIntent){
        when(intent){
            is ProfileIntent.OnChangeName -> addName(intent.username)
            is ProfileIntent.OnChangeProfileUri -> addImgUri(intent.profileUri)
        }
    }

    fun getImgUri(){
        _state.update { it.copy(profileUri = sharedPreferenceHelper.getString(SharedPreferenceHelper.IMG_URI, "") ?: "" ) }

    }

    fun getName(){
        _state.update { it.copy(username =sharedPreferenceHelper.getString(SharedPreferenceHelper.NAME, "") ?: "") }
    }

    private fun addImgUri(img: String){
        sharedPreferenceHelper.putString(SharedPreferenceHelper.IMG_URI,img)
        _state.update { it.copy(profileUri = img) }
    }

    private fun addName(name:String){
        sharedPreferenceHelper.putString(SharedPreferenceHelper.NAME,name)
        _state.update { it.copy(username = name, insertion = true) }

    }

}