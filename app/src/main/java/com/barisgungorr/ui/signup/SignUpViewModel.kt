package com.barisgungorr.ui.signup

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
    val message = MutableSharedFlow<String>()
    private var auth: FirebaseAuth = Firebase.auth


    fun checkUserInfo(email: String, password: String) {

        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            shouldNavigateToSignInScreen
        }
    }

    fun signUp(email: String, pass: String) {
        when {
            email.isEmpty() || pass.isEmpty() -> sendMessage(R.string.fillBlanksText.toString())
            pass.length < 6 -> sendMessage(R.string.passwordAlert.toString())
            isValidEmail(email).not() -> sendMessage(R.string.ınvalidAlert.toString())
            else -> auth.createUserWithEmailAndPassword(email, pass)
                .addOnSuccessListener {
                    navigateToSignInScreen()

                }.addOnFailureListener {
                    sendMessage(R.string.wrongUsernamePass.toString())

                }
        }
    }

    private fun sendMessage(message: String) {
        viewModelScope.launch {
            this@SignUpViewModel.message.emit(message)
        }
    }

    private fun navigateToSignInScreen() {

        viewModelScope.launch { shouldNavigateToSignInScreen.emit(Unit) }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.com")
        return emailRegex.matches(email)
    }
}

