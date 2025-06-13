package com.example.dagger2_app.ui.fragments.add



sealed class AddNoteIntent {
    data object OnAddNote : AddNoteIntent()
    data object OnNavigateBack : AddNoteIntent()
    data class OnSetTitle(val title: String) : AddNoteIntent()
    data class OnSetDescription(val description: String) : AddNoteIntent()
}