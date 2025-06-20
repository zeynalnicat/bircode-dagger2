package com.example.dagger2_app.ui.components

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.ColorUtils
import com.example.dagger2_app.models.ImageType
import com.example.dagger2_app.models.TextType
import com.example.home.R
import com.example.home.databinding.CustomToolbarBinding

class CustomToolBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {


    private var txtTitle: TextView
    private var root: ConstraintLayout
    private var image: ImageView
    var title: TextType
        set(value) {
            when(value){
                is TextType.TString  -> {
                    txtTitle.setText(value.text)
                }
                is TextType.TResource -> {
                    txtTitle.setText(value.text)
                }
            }
        }
        get() {
            return TextType.TString(txtTitle.text.toString())
        }

    var imageSrc: ImageType
        set(value) {
            when(value){
                is ImageType.Resource -> image.setImageResource(value.src)
                is ImageType.Drawable -> image.setImageDrawable(value.src)
            }
        }

        get() {
            return ImageType.Drawable(image.drawable)
        }
    private var buttonBack: AppCompatImageButton
    private var binding: CustomToolbarBinding =
        CustomToolbarBinding.inflate(LayoutInflater.from(context),this,true)

    init {
        txtTitle = binding.tvTitle
        root = binding.root
        image = binding.imgAction
        buttonBack = binding.imgBack as AppCompatImageButton
        attrs?.let { setAttributes(context, it, defStyleAttr) }
    }


    private fun setAttributes(context: Context, attrs: AttributeSet, defStyleAttr: Int) {

        val style =
            context.obtainStyledAttributes(attrs, R.styleable.CustomToolBar, defStyleAttr, 0)
        val txtTitle = style.getString(R.styleable.CustomToolBar_title_text)
        val txtColor = style.getColor(R.styleable.CustomToolBar_textColor, Color.BLACK)
        val containerColor = style.getColor(R.styleable.CustomToolBar_containerColor, Color.WHITE)
        val src = style.getInt(R.styleable.CustomToolBar_srcCompat,-1)

        if(src!=-1){
            image.setImageResource(src)
        }

        if (txtTitle != null) {
            this.txtTitle.text = txtTitle
        }

        if (ColorUtils.calculateLuminance(containerColor) < 0.5) {
            buttonBack.setImageResource(R.drawable.icon_back_white)
        } else {
            buttonBack.setImageResource(R.drawable.icon_back)
        }

        this.txtTitle.setTextColor(txtColor)
        this.root.setBackgroundColor(containerColor)


    }

    fun setTitle(text: String) {
        txtTitle.text = text
    }

    fun onClickListener(block: () -> Unit) {
        buttonBack.setOnClickListener {
            block()
        }
    }

}