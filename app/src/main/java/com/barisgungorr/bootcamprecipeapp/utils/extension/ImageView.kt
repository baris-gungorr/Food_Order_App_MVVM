package com.barisgungorr.bootcamprecipeapp.utils.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

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


