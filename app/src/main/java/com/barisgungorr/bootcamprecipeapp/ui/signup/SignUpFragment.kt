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
import com.barisgungorr.bootcamprecipeapp.utils.extension.click
import com.barisgungorr.bootcamprecipeapp.utils.extension.snackbar
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
                requireView().snackbar(message)
            }
        }
    }

    private fun initViews() = with(binding) {
        textViewBackSignIn.click {
            findNavController().navigate(R.id.signUpToSignIn)
        }

        buttonSignUp.click {
            val email = emailText.text.toString()
            val pass = passText.text.toString()
            viewModel.signUp(email, pass)

        }
    }
}