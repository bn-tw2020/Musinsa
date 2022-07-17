package com.homework.myapplication.presentation.common

import android.graphics.drawable.Drawable
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.homework.myapplication.util.GlideApp
import java.text.DecimalFormat

@BindingAdapter("priceFormat")
fun setPriceFormat(textView: TextView, price: Int) {
    val decimalFormat = DecimalFormat("#,###")
    textView.text = "${decimalFormat.format(price)}원"
}

@BindingAdapter("saleFormat")
fun setSaleFormat(textView: TextView, sale: Int) {
    textView.text = "$sale%"
}

@BindingAdapter("startIcon")
fun setDrawableStartIcon(textView: TextView, url: String?) {
    if (!url.isNullOrEmpty()) {
        GlideApp.with(textView.context)
            .asDrawable()
            .load(url)
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    textView.setCompoundDrawablesWithIntrinsicBounds(resource, null, null, null)
                }

                override fun onLoadCleared(placeholder: Drawable?) = Unit

            })
    }
}