package com.example.dagger2_app.models

import android.graphics.drawable.Drawable

sealed class ImageType {
    data class IResource(val src:Int): ImageType()
    data class IDrawable(val src: Drawable?): ImageType()
    data class IColor(val src:Int): ImageType()
}