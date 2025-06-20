package com.example.dagger2_app.ui.fragments.add


sealed class AddNoteIntent {
    data class OnAddNote(var title: String, var description: String) : AddNoteIntent()
    data object OnNavigateBack : AddNoteIntent()

}