package com.example.dagger2_app.ui.fragments.home

import com.example.dagger2_app.models.NoteDTO

data class HomeState (
    val notes: List<NoteDTO> = emptyList<NoteDTO>(),
)