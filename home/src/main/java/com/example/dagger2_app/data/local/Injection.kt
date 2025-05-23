package com.example.dagger2_app.data.local

import android.app.Application
import com.example.dagger2_app.HomeActivity
import com.example.dagger2_app.ui.fragments.add.AddNoteFragment
import com.example.dagger2_app.ui.fragments.home.HomeFragment

interface Injection {

    fun inject(homeActivity: HomeActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(addNoteFragment: AddNoteFragment)
}