package com.barisgungorr.bootcamprecipeapp.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private val _navigateToSign = MutableLiveData<Unit>()
    val navigateToSign: LiveData<Unit> get() = _navigateToSign

    init {
        startSplash()
    }

    private fun startSplash() {
        viewModelScope.launch {
            delay(2.seconds)
            _navigateToSign.value = Unit
        }
    }

}