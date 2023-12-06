package com.barisgungorr.bootcamprecipeapp.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.data.entity.Favorite
import com.barisgungorr.bootcamprecipeapp.databinding.ItemViewFavoriteCardBinding

class FavoriteAdapter(
    private var favoriteList: List<Favorite>,
    private val callbacks: FavoriteCallBack
) :  RecyclerView.Adapter<FavoriteViewHolder>() {

    interface FavoriteCallBack  {
        fun onDeleteFavorite(favorite:Favorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemViewFavoriteCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoriteViewHolder(binding,callbacks)
    }

    override fun getItemCount(): Int = favoriteList.size


    override fun onBindViewHolder(viewHolder: FavoriteViewHolder, position: Int) {
        val favorite = favoriteList[position]
        viewHolder.bind(favorite)
    }
}