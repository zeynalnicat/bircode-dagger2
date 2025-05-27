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
    fun MiddleActivityScreen() = ActivityScreen("First"){ Intent(it, MiddleActivity::class.java)}
    fun ProfileActivityScreen() = ActivityScreen("Second") {Intent(it, ProfileActivity::class.java)}
    fun HomeActivityScreen() = ActivityScreen("FirstNext") { Intent(it, HomeActivity::class.java).apply { flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP } }
    fun NotesFragmentScreen() = FragmentScreen{ HomeFragment() }
    fun AddNotesFragmentScreen() = FragmentScreen { AddNoteFragment()}

}