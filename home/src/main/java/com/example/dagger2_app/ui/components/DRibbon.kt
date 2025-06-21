package com.example.dagger2_app.ui.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.example.dagger2_app.models.TextType
import com.example.home.R

class DRibbon @JvmOverloads constructor(context: Context,  attributeSet: AttributeSet? = null): View(context,attributeSet) {

    private val paint = Paint().apply {
        style = Paint.Style.FILL
        isAntiAlias = true
    }


    var color: Int
        set(value) {
            paint.color = value
        }
        get() = paint.color

    private var ribbonTitle: String = ""

    var title: TextType
        set(value) {
            when(value){
                is TextType.TResource -> {
                    ribbonTitle =  resources.getString(value.text)
                }
                is TextType.TString -> ribbonTitle = value.text
            }
        }

        get() = TextType.TString(ribbonTitle)

    init {


        attributeSet?.let { setAttrs(context,it) }
    }

    private fun setAttrs(context: Context,attributeSet: AttributeSet){
       val attr = context.obtainStyledAttributes(attributeSet,R.styleable.DRibbon)
       val color = attr.getColor(R.styleable.DRibbon_color, Color.BLACK)
       val title = attr.getString(R.styleable.DRibbon_title)

       paint.color = color

       if(title!=null){
           ribbonTitle = title
       }

    }

    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
//        points.reset()
//        points.lineTo(width - 60f, 60f)
//        points.lineTo(width - 30f, 90f)



        val textPaint = Paint().apply {
            color = Color.WHITE
            textSize = 36f
            textAlign = Paint.Align.CENTER
            isAntiAlias = true
        }



        val path = Path().apply {
            moveTo(width - 200f, height.toFloat())
            lineTo(width - 100f, 0f)

            lineTo(width.toFloat(), 0f)

            lineTo(width - 100f, height.toFloat())

            close()
        }
        canvas.drawPath(path, paint)
        canvas.drawText(ribbonTitle, width -100f , height/2f, textPaint)
    }
}

