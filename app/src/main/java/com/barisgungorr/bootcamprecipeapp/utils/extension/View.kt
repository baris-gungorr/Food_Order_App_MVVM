package com.barisgungorr.bootcamprecipeapp.utils.extension

import android.graphics.Color
import android.view.View
import com.barisgungorr.bootcamprecipeapp.R
import com.google.android.material.snackbar.Snackbar

fun View.snack(message: String) {
    Snackbar.make(this, message, 1200)
        .setTextColor(Color.WHITE)
        .setBackgroundTint(resources.getColor(R.color.kk))
        .show()
}

