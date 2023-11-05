package com.barisgungorr.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    val message = MutableSharedFlow<String>()
    private var auth: FirebaseAuth = Firebase.auth

    fun checkUserInfo(email: String, password: String) {

        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {

        }
    }
    fun signUp(email: String, pass: String) {
        when {
            email.isEmpty() || pass.isEmpty() -> sendMessage("Fill in the blanks!")
            pass.length < 6 -> sendMessage("Password length must be minimum 6 characters long")
            isValidEmail(email).not() -> sendMessage("Invalid email address")
            else -> auth.createUserWithEmailAndPassword(email, pass)
                .addOnSuccessListener {
                    navigateToSingInScreen()

                }.addOnFailureListener {
                    sendMessage("Wrong email or password")

                }
        }
    }

    private fun sendMessage(message: String) {
        viewModelScope.launch {
            this@SignUpViewModel.message.emit(message)
        }
    }

    private fun navigateToSingInScreen() {

        viewModelScope.launch { shouldNavigateToSignInScreen.emit(Unit) }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
        return emailRegex.matches(email)
    }
}

