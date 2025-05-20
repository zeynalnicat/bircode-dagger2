package com.example.dagger2_app.ui.fragments.add

import androidx.lifecycle.ViewModel
import com.example.dagger2_app.data.local.NoteDao
import com.example.dagger2_app.models.NoteDTO
import javax.inject.Inject

class AddNoteViewModel @Inject constructor(val noteDao: NoteDao): ViewModel() {

    fun addNote(noteDTO: NoteDTO){

    }
}