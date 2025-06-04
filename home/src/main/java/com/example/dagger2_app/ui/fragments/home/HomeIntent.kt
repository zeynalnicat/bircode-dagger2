package com.example.dagger2_app.ui.fragments.home

import com.example.dagger2_app.models.NoteDTO

sealed class HomeIntent {
    data object OnGetDto: HomeIntent()
    data class OnRemoveNote(val note: NoteDTO): HomeIntent()
    data object OnFinishChain: HomeIntent()
    data class OnNavigateToAddNoteFragment(val title: String="", val description:String = ""): HomeIntent()
}