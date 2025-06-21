package com.example.dagger2_app.ui.components

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.setPadding
import com.example.home.R

class DTextField(context: Context,attributeSet: AttributeSet?=null): AppCompatEditText(context,attributeSet) {

    init {
        setPadding(24)
        setBackgroundResource(R.drawable.edit_text)
        setTextColor(resources.getColor(R.color.black))
        textSize = 14f
        attributeSet?.let { setAttrs(context,attributeSet) }
    }



    private fun setAttrs(context: Context,attributeSet: AttributeSet){

    }

}