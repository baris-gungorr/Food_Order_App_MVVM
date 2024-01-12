package com.barisgungorr.bootcamprecipeapp.ui.favorite

import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.databinding.ItemViewFavoriteCardBinding
import com.barisgungorr.bootcamprecipeapp.domain.FavoriteMeal
import com.barisgungorr.bootcamprecipeapp.utils.extension.Size
import com.barisgungorr.bootcamprecipeapp.utils.extension.load

class FavoriteViewHolder(
    private val binding: ItemViewFavoriteCardBinding,
    private val favoriteCallBacks: FavoriteAdapter.FavoriteCallBack
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(favorite: FavoriteMeal) = with(binding) {
        binding.ivMeal.load(
            imageUrl = favorite.imageUrl,
            size = Size(300, 300)
        )
        tvName.text = favorite.name
        ivDelete.setOnClickListener {
            favoriteCallBacks.onDeleteFavorite(favorite = favorite)
        }
    }
}