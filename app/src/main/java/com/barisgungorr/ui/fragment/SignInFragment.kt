package com.barisgungorr.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentOrderBinding
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth


class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        val view = binding.root

        auth = FirebaseAuth.getInstance()

        binding.textView.setOnClickListener {
            // Navigation kodlaması
        }

        binding.buttonSignIn.setOnClickListener {

            val email = binding.emailText.text.toString()
            val pass = binding.passText.text.toString()



            if (email.isNotEmpty() && pass.isNotEmpty() ) {

                    auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener{
                        if (it.isSuccessful) {
                            // Navigation geçiş kodlaması

                        }else {
                            Toast.makeText(requireContext(),it.exception.toString(), Toast.LENGTH_SHORT).show()


                        }
                    }

                }else {
                    Toast.makeText(requireContext(),"Parolalar uyuşmuyor", Toast.LENGTH_SHORT).show()
                }
        }



        return view
    }

    override fun onStart() {
        super.onStart()

        if (auth.currentUser != null) {
            //Navigation kodlaması
        }
    }
}