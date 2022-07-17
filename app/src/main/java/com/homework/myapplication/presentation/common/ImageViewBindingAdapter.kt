package com.homework.myapplication.presentation.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.homework.myapplication.util.GlideApp

@BindingAdapter("setImage")
fun setImage(imageView: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        GlideApp.with(imageView)
            .load(url)
            .into(imageView)
    }
}