package com.barisgungorr.bootcamprecipeapp.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.barisgungorr.bootcamprecipeapp.databinding.ItemViewFavoriteCardBinding
import com.barisgungorr.bootcamprecipeapp.domain.FavoriteMeal

class FavoriteAdapter(
    private val callbacks: FavoriteCallBack
) : ListAdapter<FavoriteMeal, FavoriteViewHolder>(FavoriteDiff()) {

    interface FavoriteCallBack {
        fun onDeleteFavorite(favorite: FavoriteMeal)
    }

    class FavoriteDiff : DiffUtil.ItemCallback<FavoriteMeal>() {
        override fun areItemsTheSame(oldItem: FavoriteMeal, newItem: FavoriteMeal): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FavoriteMeal, newItem: FavoriteMeal): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding =
            ItemViewFavoriteCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding, callbacks)
    }

    override fun onBindViewHolder(viewHolder: FavoriteViewHolder, position: Int) {
        val favorite = getItem(position)
        viewHolder.bind(favorite)
    }
}

