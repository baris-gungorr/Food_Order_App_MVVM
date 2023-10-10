package com.barisgungorr.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavView,navController)

        navController.addOnDestinationChangedListener{_,destination,_->
            when(destination.id) {
                R.id.mainFragment-> {
                    binding.bottomNavView.visibility = View.VISIBLE
                }
                R.id.signInFragment ,
                    R.id.signUpFragment,
                R.id.splashFragment,
                     R.id.orderFragment,
                R.id.favoriteFragment,
                    R.id.detailsFragment,

                -> {
                    binding.bottomNavView.visibility = View.GONE
                         binding.bottomNavView.visibility = View.GONE
                    binding.bottomNavView.visibility = View.GONE
                         binding.bottomNavView.visibility = View.GONE
                    binding.bottomNavView.visibility = View.GONE
                        binding.bottomNavView.visibility = View.GONE

                }
            }
        }
    }
}

