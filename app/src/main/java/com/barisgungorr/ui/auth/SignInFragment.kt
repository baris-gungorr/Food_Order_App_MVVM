package com.barisgungorr.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentSignInBinding
import com.barisgungorr.utils.Extension.snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
       return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        auth.currentUser?.let {
            findNavController().navigate(R.id.signToMain)
        }

        binding.textViewSign.setOnClickListener {
            findNavController().navigate(R.id.signToSignUp)
        }

        checkUserInfo()

    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
        return emailRegex.matches(email)
    }

    private fun checkUserInfo() {
       with(binding) {
           buttonSignIn.setOnClickListener {

            val email = emailText.text.toString()
            val pass = passText.text.toString()

            if (email.isNotEmpty() && isValidEmail(email) && pass.length >= 6) {
                if (email.endsWith(".com")) {
                    signIn(email, pass)
                } else {
                    requireView().snackbar("Missing email address!")
                }
            }
            else if (email.isNotEmpty() && isValidEmail(email) && pass.length < 6){
                requireView().snackbar("Password length must be minimum 6 characters long")
            }
            else if (email.isEmpty() && pass.isEmpty()){
                requireView().snackbar("Fill in the blanks!")
            }
            else {
                requireView().snackbar("Missing email address or password!")
            }
           }
        }
    }

private fun signIn(email:String, password:String){
    auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
        findNavController().navigate(R.id.signToMain)
        requireView().snackbar("Sign in successfully!")
    }.addOnFailureListener {
        requireView().snackbar("Please sign up!")
    }
}
}