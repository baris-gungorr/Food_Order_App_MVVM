package com.barisgungorr.bootcamprecipeapp.ui.home

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.data.entity.Yemekler
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentHomeCardBinding
import com.bumptech.glide.Glide

class HomeViewHolder(
    val binding: FragmentHomeCardBinding,
    private val foodCallbacks: HomeAdapter.FoodCallback,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(food: Yemekler) {
        val context = binding.root.context
        binding.imageTitle.text = food.meals_name
        setupFoodInfo(context = context, foodPrice = food.meals_price)
        setDetailClickListener(food)
        loadImage(context = context, food = food)
    }

    private fun loadImage(context: Context, food: Yemekler) {
        val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${food.meals_image_name}"
        Glide.with(context)
            .load(imageUrl)
            .override(300, 300)
            .into(binding.imageMeal)
    }

    @SuppressLint("StringFormatMatches")
    private fun setupFoodInfo(context: Context, foodPrice: Int) {
        val priceText = context.getString(R.string.price, foodPrice)
        binding.imagePrice.text = priceText
    }

    private fun setDetailClickListener(food: Yemekler) {
        binding.cardView.setOnClickListener {
            foodCallbacks.onClickDetail(food)
        }
    }
}