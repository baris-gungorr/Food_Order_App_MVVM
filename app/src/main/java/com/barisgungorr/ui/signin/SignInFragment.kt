package com.barisgungorr.ui.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentSignInBinding
import com.barisgungorr.utils.extension.click
import com.barisgungorr.utils.extension.snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding

    private val viewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observe()
        viewModel.checkUserInfo()
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.shouldNavigateToMainScreen.collectLatest {
                findNavController().navigate(R.id.signToMain)
            }
        }
        lifecycleScope.launch {
            viewModel.message.collectLatest { message ->
                requireView().snackbar(message)
            }
        }
    }

    private fun initViews() = with(binding) {

        textViewSign.click {
            findNavController().navigate(R.id.signToSignUp)
        }

        buttonSignIn.click {
            val email = emailText.text.toString()
            val password = passText.text.toString()
            viewModel.signIn(email, password)
        }

    }
}