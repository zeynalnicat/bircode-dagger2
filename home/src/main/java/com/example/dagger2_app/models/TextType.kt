package com.example.dagger2_app.models


sealed class TextType{
    data class TString(val text:String): TextType()
    data class TResource(val text:Int): TextType()
}






