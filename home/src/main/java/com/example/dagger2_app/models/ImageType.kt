package com.example.dagger2_app.models

sealed class ImageType {
    data class Resource(val src:Int): ImageType()
    data class Drawable(val src: android.graphics.drawable.Drawable): ImageType()
}