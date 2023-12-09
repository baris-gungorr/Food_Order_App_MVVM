package com.barisgungorr.bootcamprecipeapp.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentSignUpBinding
import com.barisgungorr.bootcamprecipeapp.utils.extension.snack
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observe()
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.shouldNavigateToSignInScreen.collectLatest {
                findNavController().navigate(R.id.signUpToSignIn)
            }
        }
        lifecycleScope.launch {
            viewModel.message.collectLatest { message ->
                requireView().snack(getString(message))

            }
        }
    }

    private fun initViews() = with(binding) {
        tvBack.setOnClickListener {
            findNavController().navigate(R.id.signUpToSignIn)
        }

        btnSignUp.setOnClickListener {
            val email = emailText.text.toString()
            val pass = passwordTv.text.toString()
            viewModel.signUp(email, pass)

        }
    }
}