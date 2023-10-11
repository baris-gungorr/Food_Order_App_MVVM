package com.barisgungorr.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentDetailsBinding
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentMainBinding
import com.barisgungorr.ui.viewmodel.DetailsViewModel
import com.barisgungorr.ui.viewmodel.MainViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel : DetailsViewModel
    private var piece = 0
    private  var boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        binding.buttonFavoriteNull.setOnClickListener {

            binding.buttonFavoriteNull.setImageResource(R.drawable.baseline_favorite_24)
            Toast.makeText(requireContext(),"ADD YOUR FAVORİTE!",Toast.LENGTH_LONG).show()

        }

        val bundle : DetailsFragmentArgs by navArgs()
        val getMeals = bundle.meal

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${getMeals.yemek_resim_adi}"
        Glide.with(this).load(url).into(binding.imageViewMeals)

        binding.textMealsName.text = getMeals.yemek_adi
        binding.textViewPrice.text = "${getMeals.yemek_fiyat} ₺"
        binding.mealsPieceText.text = "${piece}"


        binding.buttonMinus.setOnClickListener {
            if(piece > 1){
                piece--
                binding.mealsPieceText.text = "${piece}"
                binding.textViewPrice.text = "${piece * getMeals.yemek_fiyat.toDouble()} ₺"
            }
        }

        binding.buttonPlus.setOnClickListener {
            piece++
            binding.mealsPieceText.text = "${piece}"
            binding.textViewPrice.text = "${piece * getMeals.yemek_fiyat.toDouble()} ₺"
        }



        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: DetailsViewModel by viewModels()
        viewModel = tempViewModel
    }

}