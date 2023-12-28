package com.barisgungorr.bootcamprecipeapp.ui.home

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.MealResponse
import com.barisgungorr.bootcamprecipeapp.databinding.ItemViewHomeCardBinding
import com.barisgungorr.bootcamprecipeapp.utils.extension.Size
import com.barisgungorr.bootcamprecipeapp.utils.extension.load

class HomeViewHolder(
    private val binding: ItemViewHomeCardBinding,
    private val foodCallbacks: HomeAdapter.FoodCallback,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(meal: MealResponse) {
        val context = binding.root.context
        binding.ivTitle.text = meal.name
        setupFoodInfo(context = context, foodPrice = meal.price)
        setDetailClickListener(meal)
        loadImage(food = meal)
    }

    private fun loadImage(food: MealResponse) {
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.imageName}"
        binding.iv.load(url,size = Size(300,300))
    }


    private fun setupFoodInfo(context: Context, foodPrice: Int) {
        val priceText = context.getString(R.string.home_page_price, foodPrice)
        binding.tvPrice.text = priceText
    }

    private fun setDetailClickListener(food: MealResponse) {
        binding.cardView.setOnClickListener {
            foodCallbacks.onClickDetail(food)
        }
    }
}
