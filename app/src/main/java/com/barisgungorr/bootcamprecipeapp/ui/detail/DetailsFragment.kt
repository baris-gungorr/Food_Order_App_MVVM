package com.barisgungorr.bootcamprecipeapp.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentDetailsBinding
import com.barisgungorr.bootcamprecipeapp.data.entity.Meal
import com.barisgungorr.bootcamprecipeapp.utils.constans.AppConstants
import com.barisgungorr.bootcamprecipeapp.utils.extension.click
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var getMeals: Meal



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

    private fun observe() {

        viewModel.piece.observe(viewLifecycleOwner) { newPiece ->

            binding.mealsPieceText.text = "$newPiece"
            binding.textViewPrice.text = "${newPiece * getMeals.price.toDouble()} ₺"
        }
    }

    private fun initViews() = with(binding) {
        val bundle: DetailsFragmentArgs by navArgs()
        getMeals = bundle.meal

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${getMeals.imageName}"
        Glide.with(this@DetailsFragment).load(url).into(imageViewMeals)

        textMealsName.text = getMeals.name
        textViewPrice.text = "${getMeals.price} ₺"
        mealsPieceText.text = "${viewModel.piece.value}"


        buttonFavoriteNull.click {

            buttonFavoriteNull.setImageResource(R.drawable.baseline_favorite_24)
            Toast.makeText(requireContext(), "ADD YOUR FAVORİTE!", Toast.LENGTH_LONG).show()

            viewModel.save(getMeals.id, getMeals.name, getMeals.imageName)
        }

        buttonMinus.click {
            viewModel.buttonMinus()
        }

        buttonPlus.click {
            viewModel.buttonPlus()
        }

        buttonAddCard.click {
            val isAlreadyInCart = viewModel.isProductInBasket(getMeals.name)
            if (isAlreadyInCart) {
                Toast.makeText(requireContext(), R.string.availableCard, Toast.LENGTH_LONG).show()

            } else {
                viewModel.piece.value?.let {
                    viewModel.addMeals(
                        getMeals.name, getMeals.imageName, getMeals.price,
                        it, AppConstants.USERNAME
                    )
                }
                Toast.makeText(requireContext(), R.string.addCard, Toast.LENGTH_LONG).show()
            }
        }
        imageViewBack.click {
            findNavController().navigate(R.id.detailsToMain)
        }
    }
}

