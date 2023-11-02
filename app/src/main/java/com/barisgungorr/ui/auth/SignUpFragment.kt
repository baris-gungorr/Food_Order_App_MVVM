package com.barisgungorr.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentSignUpBinding
import com.barisgungorr.utils.extension.snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        binding.textView2.setOnClickListener {
            findNavController().navigate(R.id.signUpToSignIn)
        }
        checkUserInfo()
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
        return emailRegex.matches(email)
    }

    private fun checkUserInfo() {
        with(binding) {
            binding.buttonSignUp.setOnClickListener {
                val email = emailText.text.toString()
                val pass = passText.text.toString()

                if (email.isNotEmpty() && isValidEmail(email ) && pass.length >= 6) {
                    if (email.endsWith(".com")) {
                        signUp(email, pass)
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
    private fun signUp(email:String, password:String){
        auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
            findNavController().navigate(R.id.signUpToSignIn)
            requireView().snackbar("Sign up successfully!")
        }.addOnFailureListener {
            requireView().snackbar("Please sign in!")
        }
    }
}
