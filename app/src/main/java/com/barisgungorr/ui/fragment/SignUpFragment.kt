package com.barisgungorr.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth


class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth:FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val view = binding.root

        auth = FirebaseAuth.getInstance()
        binding.buttonSignUp.setOnClickListener {
            val email = binding.emailText.text.toString()
                val pass = binding.passText.text.toString()
            val confirmPass = binding.confirmPassText.text.toString()

            binding.textView.setOnClickListener {
                // Navigation kodlaması
            }


            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
            if (pass == confirmPass) {

                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener{
                    if (it.isSuccessful) {
                        // Navigation geçiş kodlaması

                    }else {
                        Toast.makeText(requireContext(),it.exception.toString(),Toast.LENGTH_SHORT).show()


                    }
                }

            }else {
                Toast.makeText(requireContext(),"Parolalar uyuşmuyor",Toast.LENGTH_SHORT).show()
            }
            }else {
                Toast.makeText(requireContext(),"Boş alanlara izin verilmez",Toast.LENGTH_SHORT).show()
            }

        }



        return view
    }
}