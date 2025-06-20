package com.example.profile.ui

sealed class ProfileIntent {
    data class OnChangeProfileUri(val profileUri: String) : ProfileIntent()
    data class OnChangeName(val username: String) : ProfileIntent()

}