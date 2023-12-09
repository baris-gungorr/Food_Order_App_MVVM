package com.barisgungorr.bootcamprecipeapp.ui.signin

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
    val message = MutableSharedFlow<Int>()
    private var auth: FirebaseAuth = Firebase.auth

    fun checkUserInfo() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            navigateToMainScreen()
        }
    }
    fun signIn(email: String, password: String) {
        when {
            email.isEmpty() || password.isEmpty() -> sendMessage(R.string.sign_in_fill_in_blanks)

            password.length < 6 -> sendMessage(R.string.sign_in_password_alert)
            isValidEmail(email).not() -> sendMessage(R.string.sign_in_invalid_alert)
            else -> auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { navigateToMainScreen() }
                .addOnFailureListener { sendMessage(R.string.sign_in_wrong_email_password) }
        }
    }

    private fun sendMessage(message: Int) {
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