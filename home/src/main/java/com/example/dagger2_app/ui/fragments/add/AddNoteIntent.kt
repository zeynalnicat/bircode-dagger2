package com.example.dagger2_app.ui.fragments.add

import com.example.dagger2_app.models.NoteDTO


sealed class AddNoteIntent {
    data object OnAddNote : AddNoteIntent()
    data object OnNavigateBack : AddNoteIntent()
    data class OnSetTitle(val title: String) : AddNoteIntent()
    data class OnSetDescription(val description: String) : AddNoteIntent()
}