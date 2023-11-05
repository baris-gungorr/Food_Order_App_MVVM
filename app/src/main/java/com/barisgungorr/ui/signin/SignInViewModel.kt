package com.barisgungorr.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisgungorr.bootcamprecipeapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {

    val shouldNavigateToMainScreen = MutableSharedFlow<Unit>()
    val message = MutableSharedFlow<String>()
    private var auth: FirebaseAuth = Firebase.auth

    fun checkUserInfo() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            navigateToMainScreen()
        }
    }

    fun signIn(email: String, password: String) {
        when {
            email.isEmpty() || password.isEmpty() -> sendMessage(R.string.fillBlanksText.toString())
            password.length < 6 -> sendMessage(R.string.passwordAlert.toString())
            isValidEmail(email).not() -> sendMessage(R.string.ınvalidAlert.toString())
            else -> auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { navigateToMainScreen() }
                .addOnFailureListener { sendMessage(R.string.wrongUsernamePass.toString()) }
        }
    }

    private fun sendMessage(message: String) {
        viewModelScope.launch {
            this@SignInViewModel.message.emit(message)
        }
    }

    private fun navigateToMainScreen() {
        viewModelScope.launch { shouldNavigateToMainScreen.emit(Unit) }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
        return emailRegex.matches(email)
    }

}