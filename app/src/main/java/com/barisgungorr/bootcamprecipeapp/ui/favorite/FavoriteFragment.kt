package com.barisgungorr.bootcamprecipeapp.ui.favorite

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.data.entity.Favorite
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVariables()
        observe()
        initViews()
    }

    private fun initVariables() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rv.layoutManager = layoutManager
    }

    private fun observe() {
        viewModel.favoriteList.observe(viewLifecycleOwner) { favorites ->

            val isFavoritesEmpty = favorites.isEmpty()

                binding.ivEmpty.isVisible =  isFavoritesEmpty
                binding.tvEmpty.isVisible = isFavoritesEmpty


            val adapter = FavoriteAdapter(
                favoriteList = favorites.orEmpty(),
                callbacks = object : FavoriteAdapter.FavoriteCallBack {
                    override fun onDeleteFavorite(favorite: Favorite) {
                        showDeleteFavoriteDialog(favorite)
                    }
                }
            )
            binding.rv.adapter = adapter
        }
    }
    private fun showDeleteFavoriteDialog(favorite: Favorite) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.favorite_page_title)
        builder.setMessage(getString(R.string.favorite_page_delete_tv, favorite.mealsName))
        builder.setIcon(R.drawable.ic_app_icon)
        builder.setPositiveButton(R.string.favorite_page_yes_tv) { dialog, which ->

            viewModel.deleteFavorite(mealId = favorite.mealsId)
            dialog.dismiss()
        }
        builder.setNegativeButton(R.string.home_page_button_no) { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initViews() = with(binding) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchFavorite(newText)
                return false
            }
        })
        searchView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {

                binding.searchView.isIconified = false
            }
            false
        }
        ivHome.setOnClickListener {
            findNavController().navigate(R.id.favoriteToMain)
        }
    }
    override fun onResume() {
        super.onResume()
        viewModel.getFavorites()
    }
}