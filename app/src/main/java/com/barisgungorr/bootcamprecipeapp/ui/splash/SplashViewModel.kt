package com.barisgungorr.bootcamprecipeapp.ui.splash

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private val _navigateToSign = MutableLiveData<Boolean>()
    val navigateToSign: LiveData<Boolean>
        get() = _navigateToSign
    init {
        Handler(Looper.getMainLooper()).postDelayed({
            _navigateToSign.value = true
        }, 2000)
    }

}