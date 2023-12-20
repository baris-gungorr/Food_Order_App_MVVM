package com.barisgungorr.bootcamprecipeapp.ui.signup

import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.ui.signin.SignInError

enum class SignUpError {
    FILL_IN_BLANKS,
    PASSWORD_ALERT,
    INVALID_ALERT,
    WRONG_EMAIL_PASSWORD;
    companion object {
        fun getErrorMessage(error: SignUpError): Int {
            return when (error) {
                FILL_IN_BLANKS -> R.string.sign_in_fill_in_blanks
                PASSWORD_ALERT -> R.string.sign_in_password_alert
                INVALID_ALERT -> R.string.sign_in_invalid_alert
                WRONG_EMAIL_PASSWORD -> R.string.sign_in_wrong_email_password
            }
        }
    }
}