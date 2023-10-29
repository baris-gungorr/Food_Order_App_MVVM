package com.barisgungorr.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.databinding.FavoriteCardBinding
import com.barisgungorr.data.entity.Favorite
import com.barisgungorr.ui.viewmodel.FavoriteViewModel
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class FavoriteAdapter(var viewModel: FavoriteViewModel,
                      var mContext: Context,
                      var favoriteList:List<Favorite>)
                        :  RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: FavoriteCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FavoriteCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
      return favoriteList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = favoriteList[position]
        val t = holder.binding

       t.textViewMealName.text = meal.meals_name

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${meal.meals_image_name}"
        Glide.with(mContext).load(url).into(holder.binding.imageView)

        t.mealsDelete.setOnClickListener {
            Snackbar.make(
                it, "${meal.meals_name} ${it.resources.getString(R.string.removeText)}", Snackbar.LENGTH_LONG

            ).setAction(R.string.yesText) {
                deleteF(meal.meals_id)

            }.show()
        }
    }
    private fun deleteF(meals_id:Int) {
        viewModel.deleteF(meals_id )
    }
}