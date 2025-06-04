package com.example.dagger2_app.ui.fragments.add


data class AddNoteState(
    val insertion: Boolean = false,
    val error: String = "",
    val title :String = "",
    val description:String = ""
)