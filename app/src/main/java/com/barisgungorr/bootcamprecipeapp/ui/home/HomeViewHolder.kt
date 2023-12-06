package com.barisgungorr.bootcamprecipeapp.ui.home

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.Meal
import com.barisgungorr.bootcamprecipeapp.databinding.ItemViewHomeCardBinding
import com.barisgungorr.bootcamprecipeapp.utils.extension.Size
import com.barisgungorr.bootcamprecipeapp.utils.extension.load
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
        loadImage(food = meal)
    }

    private fun loadImage(food: Meal) {
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.imageName}"
        binding.imageMeal.load(imageUrl = url,size = Size(300,300))
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