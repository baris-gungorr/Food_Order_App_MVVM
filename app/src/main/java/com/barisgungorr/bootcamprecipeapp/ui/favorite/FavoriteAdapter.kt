package com.barisgungorr.bootcamprecipeapp.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentFavoriteCardBinding
import com.barisgungorr.bootcamprecipeapp.data.entity.Favorite
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class FavoriteAdapter(
    private var viewModel: FavoriteViewModel,
    private var favoriteList: List<Favorite>
)
    :  RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: FragmentFavoriteCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentFavoriteCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = favoriteList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = favoriteList[position]
        val t = holder.binding

       t.textViewMealName.text = meal.meals_name

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${meal.meals_image_name}"
        Glide.with(holder.itemView.context).load(url).into(holder.binding.imageView)

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