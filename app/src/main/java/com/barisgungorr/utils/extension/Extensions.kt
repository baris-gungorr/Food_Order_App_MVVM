package com.barisgungorr.utils.extension

import android.graphics.Color
import android.view.View
import androidx.navigation.Navigation
import com.barisgungorr.bootcamprecipeapp.R
import com.google.android.material.snackbar.Snackbar

fun View.snackbar(message:String) {
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

    fun Navigation.transition(view: View, id: Int) {
        findNavController(view).navigate(id)
    }



