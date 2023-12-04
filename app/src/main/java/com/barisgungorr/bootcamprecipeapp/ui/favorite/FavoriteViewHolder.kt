package com.barisgungorr.bootcamprecipeapp.ui.favorite

import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.data.entity.Favorite
import com.barisgungorr.bootcamprecipeapp.databinding.ItemViewFavoriteCardBinding
import com.bumptech.glide.Glide

class FavoriteViewHolder(
    private val binding: ItemViewFavoriteCardBinding,
    private val favoriteCallBacks: FavoriteAdapter.FavoriteCallBack
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(favorite: Favorite) = with(binding) {

        val context = binding.root.context

        val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${favorite.mealsImageName}"

        Glide.with(context).load(imageUrl).override(300, 300).into(binding.favoriteImageMeal)

        favoriteTextMealName.text = favorite.mealsName


        favoriteMealsDelete.setOnClickListener {
            favoriteCallBacks.onDeleteFavorite(favorite = favorite)

        }
    }
}