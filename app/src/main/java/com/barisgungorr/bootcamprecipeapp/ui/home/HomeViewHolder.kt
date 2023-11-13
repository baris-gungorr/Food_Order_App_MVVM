package com.barisgungorr.bootcamprecipeapp.ui.home

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.data.entity.Meal
import com.barisgungorr.bootcamprecipeapp.databinding.ItemViewHomeCardBinding
import com.bumptech.glide.Glide

class HomeViewHolder(
    private val binding: ItemViewHomeCardBinding,
    private val foodCallbacks: HomeAdapter.FoodCallback,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(meal: Meal) {
        val context = binding.root.context
        binding.imageTitle.text = meal.name
        setupFoodInfo(context = context, foodPrice = meal.price)
        setDetailClickListener(meal)
        loadImage(context = context, food = meal)
    }

    private fun loadImage(context: Context, food: Meal) {
        val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${food.imageName}"
        Glide.with(context)
            .load(imageUrl)
            .override(300, 300)
            .into(binding.imageMeal)
    }

    private fun setupFoodInfo(context: Context, foodPrice: Int) {
        val priceText = context.getString(R.string.price, foodPrice)
        binding.imagePrice.text = priceText
    }

    private fun setDetailClickListener(food: Meal) {
        binding.cardView.setOnClickListener {
            foodCallbacks.onClickDetail(food)
        }
    }
}