package com.example.dagger2_app.ui.components

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import com.example.home.R

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
class CustomToolBar @JvmOverloads constructor(
    context: Context,
                     attrs: AttributeSet? = null,
                     defStyleAttr: Int = 0): LinearLayout(context,attrs,defStyleAttr) {


    private var txtTitle: TextView
    private var root: ConstraintLayout
    private var buttonBack: AppCompatImageButton

    init {
        inflate(context,R.layout.custom_toolbar,this)
        txtTitle = findViewById<TextView>(R.id.tvTitle)
        root = findViewById<ConstraintLayout>(R.id.root)
        buttonBack = findViewById<AppCompatImageButton>(R.id.imgBack)
        attrs?.let { setAttributes(context, it,defStyleAttr) }
    }

    private fun setAttributes(context: Context,attrs: AttributeSet,defStyleAttr: Int){

         val style = context.obtainStyledAttributes(attrs,R.styleable.CustomToolBar,defStyleAttr,0)
         val txtTitle = style.getString(R.styleable.CustomToolBar_title_text,)
         val txtColor = style.getColor(R.styleable.CustomToolBar_textColor, Color.BLACK)
         val containerColor = style.getColor(R.styleable.CustomToolBar_containerColor, Color.WHITE)
         if(txtTitle!=null){
             this.txtTitle.text = txtTitle
         }

        if(ColorUtils.calculateLuminance(containerColor)<0.5){
            buttonBack.setImageResource(R.drawable.icon_back_white)
        }else{
            buttonBack.setImageResource(R.drawable.icon_back)
        }

        this.txtTitle.setTextColor(txtColor)
        this.root.setBackgroundColor(containerColor)


    }

    fun setTitle(text:String){
         txtTitle.text = text
    }

    fun onClickListener(block:()->Unit){
        buttonBack.setOnClickListener {
            block()
        }
    }

}