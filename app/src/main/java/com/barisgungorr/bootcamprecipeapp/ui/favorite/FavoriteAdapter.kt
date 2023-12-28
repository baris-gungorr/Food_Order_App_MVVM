package com.barisgungorr.bootcamprecipeapp.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.data.entity.Favorite
import com.barisgungorr.bootcamprecipeapp.databinding.ItemViewFavoriteCardBinding
import com.bumptech.glide.Glide

class FavoriteAdapter(
    private val callbacks: FavoriteCallBack
) :  ListAdapter<Favorite, FavoriteViewHolder>(FavoriteDiff()) {

    interface FavoriteCallBack  {
        fun onDeleteFavorite(favorite:Favorite)
    }

    class FavoriteDiff : DiffUtil.ItemCallback<Favorite>() {
        override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
            return oldItem.mealsId == newItem.mealsId
        }

        override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemViewFavoriteCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoriteViewHolder(binding,callbacks)
    }

    override fun onBindViewHolder(viewHolder: FavoriteViewHolder, position: Int) {
        val favorite = getItem(position)
        viewHolder.bind(favorite)
    }
}
