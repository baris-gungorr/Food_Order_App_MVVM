package com.barisgungorr.utils

import android.graphics.Color
import android.view.View
import com.barisgungorr.bootcamprecipeapp.R
import com.google.android.material.snackbar.Snackbar

fun View.snackbar(message:String){
    Snackbar.make(this,message,1200)
        .setTextColor(Color.WHITE)
        .setBackgroundTint(resources.getColor(R.color.kk))
        .show()
}