package com.example.profile.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.profile.data.local.ProfileDao
import com.example.profile.data.local.SharedPreferenceHelper
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val sharedPreferenceHelper: SharedPreferenceHelper): ViewModel() {

    private val _imgUri = MutableLiveData<String>("")
    val imgUri : LiveData<String> get() = _imgUri

    private val _name = MutableLiveData<String>("")
    val name : LiveData<String> get() = _name

    fun getImgUri(){
        _imgUri.value = sharedPreferenceHelper.getString(SharedPreferenceHelper.IMG_URI, "")
    }

    fun getName(){
        _name.value = sharedPreferenceHelper.getString(SharedPreferenceHelper.NAME, "")
    }

    fun addImgUri(img: String){
        sharedPreferenceHelper.putString(SharedPreferenceHelper.IMG_URI,img)
        _imgUri.value = img
    }

    fun addName(name:String){
        sharedPreferenceHelper.putString(SharedPreferenceHelper.NAME,name)
        _name.value = name
    }

}