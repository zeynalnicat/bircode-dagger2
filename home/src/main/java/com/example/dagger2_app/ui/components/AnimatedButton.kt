package com.example.dagger2_app.ui.components

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import com.example.home.R

@SuppressLint("ClickableViewAccessibility")
class AnimatedButton @JvmOverloads constructor(context: Context, attrs: AttributeSet?=null):
    AppCompatButton(context,attrs) {

        init {
            setBackgroundResource(R.drawable.bg_background)
            setTextColor(resources.getColor(R.color.white))
            isAllCaps = false
            setPadding(16,8,8,16)


            setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                            v.animate().scaleX(0.95f).scaleY(0.95f).setDuration(100).start()
                        }
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                            v.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
                        }
                    }
                }
                false
            }

        }
}