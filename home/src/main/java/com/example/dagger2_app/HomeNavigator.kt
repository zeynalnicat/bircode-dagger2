package com.example.dagger2_app

import android.content.Intent
import com.example.dagger2_app.ui.fragments.add.AddNoteFragment
import com.example.dagger2_app.ui.fragments.home.HomeFragment
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen.Companion.invoke

object HomeNavigator {
    fun HomeActivityScreen() = ActivityScreen{ Intent(it, HomeActivity::class.java) }
    fun NotesFragmentScreen() = FragmentScreen{ HomeFragment() }
    fun AddNotesFragmentScreen() = FragmentScreen { AddNoteFragment()}

}