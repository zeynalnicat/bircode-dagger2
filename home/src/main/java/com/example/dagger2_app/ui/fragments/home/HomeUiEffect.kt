package com.example.dagger2_app.ui.fragments.home

sealed class HomeUiEffect {

    data class ShowSnackbar(var message:String): HomeUiEffect()
}