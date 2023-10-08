package com.barisgungorr.utils.Extension

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import com.barisgungorr.bootcamprecipeapp.R
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

fun View.snackbar(message:String){
    Snackbar.make(this,message,1200)
        .setTextColor(Color.WHITE)
        .setBackgroundTint(resources.getColor(R.color.kk))
        .show()

    fun ImageView.loadImage(url: String?) {
        Glide.with(this.context).load(url).into(this)
    }
}