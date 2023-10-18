package com.barisgungorr.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.databinding.FavoriteCardBinding
import com.barisgungorr.data.entity.Yemekler
import com.barisgungorr.ui.viewmodel.FavoriteViewModel
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class FavoriteAdapter(var viewModel: FavoriteViewModel,
                        var mContext: Context,

                        var mealList:List<Yemekler>)
                        :  RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: FavoriteCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FavoriteCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
      return mealList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = mealList[position]
        val t = holder.binding


       t.textViewMealName.text = meal.yemek_adi

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${meal.yemek_resim_adi}"
        Glide.with(mContext).load(url).into(holder.binding.imageView)

        t.mealsDelete.setOnClickListener {
            Snackbar.make(
                it, "${meal.yemek_adi} Remove From Card ?", Snackbar.LENGTH_LONG
            ).setAction("YES") {
             //   viewModel.delete(meal.yemek_id,"BarisGungor")

            }.show()
        }

    }

}