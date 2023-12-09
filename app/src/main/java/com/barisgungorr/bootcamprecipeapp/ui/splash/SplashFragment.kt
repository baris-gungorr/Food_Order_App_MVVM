package com.barisgungorr.bootcamprecipeapp.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentSplashBinding
import com.barisgungorr.bootcamprecipeapp.ui.signin.SignInViewModel

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)

        viewModel.navigateToSign.observe(viewLifecycleOwner) { shouldNavigate ->
            if (shouldNavigate) {
                val action = SplashFragmentDirections.splashToSign()
                findNavController().navigate(action)
            }
        }
        return binding.root
    }
}