package com.example.core.extensions

import android.widget.EditText

fun EditText.setTextIfChanged(newText: String) {
    if (text.toString() != newText) {
        val cursorPos = newText.length
        setText(newText)
        setSelection(cursorPos.coerceAtMost(newText.length))
    }
}