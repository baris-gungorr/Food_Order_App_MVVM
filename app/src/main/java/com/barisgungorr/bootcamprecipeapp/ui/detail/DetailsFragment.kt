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
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.MealResponse
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
    private lateinit var meals: MealResponse
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observe()
    }

    private fun observe() = with(binding) {
        viewModel.piece.observe(viewLifecycleOwner) { newPiece ->

            mealsPieceText.text = "$newPiece"
            tvPrice.text = getString(R.string.home_page_price, meals.price * newPiece)

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

    private fun initViews() = with(binding) {
        val bundle: DetailsFragmentArgs by navArgs()
        meals = bundle.meal

        val photo = "http://kasimadalan.pe.hu/yemekler/resimler/${meals.imageName}"
        ivMeals.load(photo)

        tvName.text = meals.name
        tvPrice.text = getString(R.string.home_page_price, meals.price)
        mealsPieceText.text = "${viewModel.piece.value}"


        ivHome.setOnClickListener {
            findNavController().navigate(R.id.detailsToMain)
        }

        btnFavEmpty.setOnClickListener {

            btnFavEmpty.setImageResource(R.drawable.baseline_favorite_24)

            requireView().snack(getString(R.string.favorite_page_add_favorite))
            viewModel.save(meals.id, meals.name, meals.imageName)

        }
        btnMinus.setOnClickListener { viewModel.buttonDecrease() }

        btnPlus.setOnClickListener { viewModel.buttonQuantity() }

        btnAddCard.setOnClickListener {
            viewModel.handleButtonClick(meals)
        }
    }
}

