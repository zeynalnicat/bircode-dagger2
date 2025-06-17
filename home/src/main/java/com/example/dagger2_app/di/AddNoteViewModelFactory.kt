package com.example.dagger2_app.di

import com.example.dagger2_app.models.NoteDTO
import com.example.dagger2_app.ui.fragments.add.AddNoteViewModel
import dagger.assisted.AssistedFactory

@AssistedFactory
interface AddNoteViewModelFactory {
    fun create(noteDTO: NoteDTO): AddNoteViewModel
}