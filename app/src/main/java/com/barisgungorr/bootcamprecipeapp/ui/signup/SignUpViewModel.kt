package com.barisgungorr.bootcamprecipeapp.ui.signup

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
class SignUpViewModel @Inject constructor() : ViewModel() {
    val shouldNavigateToSignInScreen = MutableSharedFlow<Unit>()
    val message = MutableSharedFlow<Int>()
    private var auth: FirebaseAuth = Firebase.auth


    fun signUp(email: String, pass: String) {
        when {
            email.isEmpty() || pass.isEmpty() -> sendMessage(R.string.sign_in_fill_in_blanks)
            pass.length < 6 -> sendMessage(R.string.sign_in_password_alert)
            isValidEmail(email).not() -> sendMessage(R.string.sign_in_invalid_alert)
            else -> auth.createUserWithEmailAndPassword(email, pass)
                .addOnSuccessListener {
                    navigateToSignInScreen()
                }.addOnFailureListener {
                    sendMessage(R.string.sign_in_wrong_email_password)
                }
        }
    }

    private fun sendMessage(message: Int) {
        viewModelScope.launch {
            this@SignUpViewModel.message.emit(message)
        }
    }

    private fun navigateToSignInScreen() {
        viewModelScope.launch { shouldNavigateToSignInScreen.emit(Unit) }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.")
        return emailRegex.matches(email)
    }
}

