package com.example.dagger2_app.models

sealed class ImageType {
    data class IResource(val src:Int): ImageType()
    data class IDrawable(val src: android.graphics.drawable.Drawable): ImageType()
    data class IColor(val src:Int): ImageType()
}