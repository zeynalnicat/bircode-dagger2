package com.example.profile.data

import com.example.profile.ProfileActivity

interface ProfileInjection {

    fun inject(profileActivity: ProfileActivity)
}