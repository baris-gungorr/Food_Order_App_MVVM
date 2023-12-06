package com.barisgungorr.bootcamprecipeapp.utils.extension

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import com.barisgungorr.bootcamprecipeapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar

fun View.snack(message: String) {
    Snackbar.make(this, message, 1200)
        .setTextColor(Color.WHITE)
        .setBackgroundTint(resources.getColor(R.color.kk))
        .show()
}
fun View.click(func: () -> Unit) {
    this.setOnClickListener {
        func()
    }
}
fun ImageView.load(
    imageUrl: String,
   size: Size? = null
) {
    var requestOptions = RequestOptions()
    if (size != null) {
        requestOptions = requestOptions.override(width, height)
    }
    Glide.with(this.context)
        .load(imageUrl)
        .apply(requestOptions)
        .into(this)
}
 data class Size(val width: Int, val height: Int)


