package com.barisgungorr.bootcamprecipeapp.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisgungorr.bootcamprecipeapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {

    val shouldNavigateToMainScreen = MutableSharedFlow<Unit>()

    private var auth: FirebaseAuth = Firebase.auth

    private val _error = MutableSharedFlow<SignInError>()
    val error: SharedFlow<SignInError> get() = _error

    fun checkUserInfo() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            navigateToMainScreen()
        }
    }
    fun signIn(email: String, password: String) {
        when {
            email.isEmpty() || password.isEmpty() -> sendError(SignInError.FILL_IN_BLANKS)

            password.length < 6 -> sendError(SignInError.PASSWORD_ALERT)
            isValidEmail(email).not() -> sendError(SignInError.INVALID_ALERT)

            else -> auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { navigateToMainScreen() }
                .addOnFailureListener { sendError(SignInError.WRONG_EMAIL_PASSWORD) }
        }
    }
    private fun navigateToMainScreen() {
        viewModelScope.launch { shouldNavigateToMainScreen.emit(Unit) }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
        return emailRegex.matches(email)
    }
    private fun sendError(error: SignInError) {
        viewModelScope.launch {
            _error.emit(error)
        }
    }
}