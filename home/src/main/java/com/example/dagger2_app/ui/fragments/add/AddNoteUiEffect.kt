package com.example.dagger2_app.ui.fragments.add

sealed class AddNoteUiEffect {

    data class ShowSnackbar(var message: String) : AddNoteUiEffect()
    data object NotifyInsertion : AddNoteUiEffect()
}