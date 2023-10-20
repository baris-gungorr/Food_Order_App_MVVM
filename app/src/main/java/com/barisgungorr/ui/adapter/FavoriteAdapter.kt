package com.barisgungorr.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.databinding.FavoriteCardBinding
import com.barisgungorr.data.entity.Favorite
import com.barisgungorr.data.entity.Yemekler
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

       t.textViewMealName.text = meal.yemek_adi

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${meal.yemek_resim_adi}"
        Glide.with(mContext).load(url).into(holder.binding.imageView)

        t.mealsDelete.setOnClickListener {
            Snackbar.make(
                it, "${meal.yemek_adi} Remove From Card ?", Snackbar.LENGTH_LONG
            ).setAction("YES") {
                deleteF(meal.yemek_id)

            }.show()
        }
    }
    fun deleteF(yemek_id:Int) {
        viewModel.deleteF(yemek_id )
    }
}