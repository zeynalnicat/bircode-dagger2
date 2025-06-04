package com.example.dagger2_app.ui.fragments.add

import com.example.dagger2_app.models.NoteDTO


sealed class AddNoteIntent {
    data class OnAddNote(val note: NoteDTO): AddNoteIntent()
    data object OnClearState: AddNoteIntent()
    data object OnNavigateBack: AddNoteIntent()
}