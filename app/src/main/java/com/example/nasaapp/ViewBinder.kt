package com.example.nasaapp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import com.example.nasaapp.ui.main.MainViewModel
import com.google.android.material.textfield.TextInputLayout

object ViewBinder {
    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(view:ImageView,url:String?){
        view.load(url)
    }
}