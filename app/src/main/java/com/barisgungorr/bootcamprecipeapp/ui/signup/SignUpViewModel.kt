package com.barisgungorr.bootcamprecipeapp.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.ui.signin.SignInError
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {

    val shouldNavigateToSignInScreen = MutableSharedFlow<Unit>()

    private var auth: FirebaseAuth = Firebase.auth

    private val _error = MutableSharedFlow<SignUpError>()
    val error: SharedFlow<SignUpError> get() = _error


    fun signUp(email: String, pass: String) {
        when {
            email.isEmpty() || pass.isEmpty() -> sendError(SignUpError.FILL_IN_BLANKS)
            pass.length < 6 -> sendError(SignUpError.PASSWORD_ALERT)
            isValidEmail(email).not() -> sendError(SignUpError.INVALID_ALERT)
            else -> auth.createUserWithEmailAndPassword(email, pass)
                .addOnSuccessListener {
                    navigateToSignInScreen()
                }.addOnFailureListener {
                    sendError(SignUpError.WRONG_EMAIL_PASSWORD)
                }
        }
    }

    private fun navigateToSignInScreen() {
        viewModelScope.launch { shouldNavigateToSignInScreen.emit(Unit) }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.")
        return emailRegex.matches(email)
    }

    private fun sendError(error: SignUpError) {
        viewModelScope.launch {
            _error.emit(error)
        }
    }
}

