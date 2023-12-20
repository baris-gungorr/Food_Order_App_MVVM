package com.barisgungorr.bootcamprecipeapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentDetailsBinding
import com.barisgungorr.bootcamprecipeapp.utils.extension.load
import com.barisgungorr.bootcamprecipeapp.utils.extension.snack
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVariables()
        observe()
        initViews()

    }
    private fun initVariables() {
        viewModel.initMeal(args.meal)
    }


    private fun initViews() = with(binding) {

        val meal = args.meal
        val photo = "http://kasimadalan.pe.hu/yemekler/resimler/${meal.imageName}"
        ivMeals.load(photo)
        tvName.text = meal.name
        tvPrice.text = getString(R.string.home_page_price, meal.price)

        ivHome.setOnClickListener {
            findNavController().navigate(R.id.detailsToMain)
        }

        btnFavEmpty.setOnClickListener {
            btnFavEmpty.setImageResource(R.drawable.baseline_favorite_24)
            requireView().snack(getString(R.string.favorite_page_add_favorite))
            viewModel.save()
        }

        btnMinus.setOnClickListener { viewModel.decreaseQuantity() }

        btnPlus.setOnClickListener { viewModel.increaseQuantity() }

        btnAddCard.setOnClickListener { viewModel.addToCart() }
    }
    private fun observe() = with(binding) {

        lifecycleScope.launch {
            viewModel.uiModel.collectLatest { uiModel ->
                mealsPieceText.text = uiModel.piece.toString()
                tvPrice.text = getString(R.string.home_page_price, uiModel.price)
            }
        }

        lifecycleScope.launch {
            viewModel.message.collectLatest { message ->
                requireView().snack(getString(message))
            }
        }

        lifecycleScope.launch {
            viewModel.shouldNavigateToMainScreen.collectLatest {
                findNavController().navigate(R.id.detailsToMain)
            }
        }
    }
}

