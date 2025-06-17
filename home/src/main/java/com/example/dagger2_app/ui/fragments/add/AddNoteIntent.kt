package com.example.dagger2_app.ui.fragments.add



sealed class AddNoteIntent {
    data class OnAddNote(val isUpdate: Boolean = false) : AddNoteIntent()
    data object OnNavigateBack : AddNoteIntent()
    data class OnSetTitle(val title: String) : AddNoteIntent()
    data class OnSetDescription(val description: String) : AddNoteIntent()
    data class OnSetId(val id:Int): AddNoteIntent()
}