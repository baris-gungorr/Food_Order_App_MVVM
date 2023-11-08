package com.barisgungorr.bootcamprecipeapp.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentHomeCardBinding
import com.barisgungorr.bootcamprecipeapp.data.entity.Yemekler
import com.bumptech.glide.Glide

class HomeAdapter(
    var viewmodel: HomeViewModel,
    private var mealList: List<Yemekler>
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: FragmentHomeCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentHomeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int = mealList.size


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val meal = mealList[position]

        holder.binding.cardView.setOnClickListener {
            val go = HomeFragmentDirections.actionMainFragmentToDetailsFragment(meal = meal)
            Navigation.findNavController(it).navigate(go)
        }

        holder.binding.imageTitle.text = meal.meals_name
        holder.binding.imagePrice.text = "Price: ${meal.meals_price} $"

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${meal.meals_image_name}"
        Glide.with(holder.itemView.context).load(url).into(holder.binding.imageMeal)

    }
}
