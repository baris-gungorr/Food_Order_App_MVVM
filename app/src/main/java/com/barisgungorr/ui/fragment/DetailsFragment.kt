package com.barisgungorr.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentDetailsBinding
import com.barisgungorr.ui.viewmodel.DetailsViewModel
import com.barisgungorr.utils.extension.click
import com.barisgungorr.utils.extension.transition
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()
    private var piece = 1

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        val bundle: DetailsFragmentArgs by navArgs()
        val getMeals = bundle.meal

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${getMeals.yemek_resim_adi}"
        Glide.with(this).load(url).into(binding.imageViewMeals)

        binding.textMealsName.text = getMeals.yemek_adi
        binding.textViewPrice.text = "${getMeals.yemek_fiyat} ₺"
        binding.mealsPieceText.text = "$piece"

        binding.buttonFavoriteNull.setOnClickListener {

            binding.buttonFavoriteNull.setImageResource(R.drawable.baseline_favorite_24)
            Toast.makeText(requireContext(), "ADD YOUR FAVORİTE!", Toast.LENGTH_LONG).show()

            viewModel.save(getMeals.yemek_id, getMeals.yemek_adi, getMeals.yemek_resim_adi)
        }

        binding.buttonMinus.click {
            if (piece > 1) {
                piece--
                binding.mealsPieceText.text = "$piece"
                binding.textViewPrice.text = "${piece * getMeals.yemek_fiyat.toDouble()} ₺"
            }
        }

        binding.buttonPlus.click {
            piece++
            binding.mealsPieceText.text = "$piece"
            binding.textViewPrice.text = "${piece * getMeals.yemek_fiyat.toDouble()} ₺"

        }

        binding.buttonAddCard.click {
            val isAlreadyInCart = viewModel.isProductInBasket(getMeals.yemek_adi)
            if (isAlreadyInCart) {
                Toast.makeText(requireContext(), R.string.AvailableCard, Toast.LENGTH_LONG).show()

            } else {
                viewModel.addMeals(
                    getMeals.yemek_adi,
                    getMeals.yemek_resim_adi,
                    getMeals.yemek_fiyat,
                    piece,
                    "barisGungor"
                )
                Toast.makeText(requireContext(), R.string.AddCard, Toast.LENGTH_LONG).show()
            }
        }

        binding.imageViewBack.click {
            this.view?.let { Navigation.transition(it, R.id.detailsToMain) }
        }

        return binding.root
    }
}

