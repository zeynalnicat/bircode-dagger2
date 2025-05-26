package com.example

import android.content.Intent
import com.example.dagger2_app.HomeActivity
import com.example.dagger2_app.MiddleActivity
import com.example.dagger2_app.ui.fragments.add.AddNoteFragment
import com.example.dagger2_app.ui.fragments.home.HomeFragment
import com.example.profile.ProfileActivity
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun First() = ActivityScreen("First"){ Intent(it, MiddleActivity::class.java) }
    fun Second() = ActivityScreen("Second") {Intent(it, ProfileActivity::class.java)}
    fun FirstNextActivity() = ActivityScreen("FirstNext") { Intent(it, HomeActivity::class.java) }
    fun NotesScreen() = FragmentScreen{ HomeFragment() }
    fun AddNoteScreen() = FragmentScreen { AddNoteFragment()}
}