package com.example.dagger2_app.ui.fragments.add


data class AddNoteState(
    val id : Int = -1,
    val title: String = "",
    val description: String = ""
)