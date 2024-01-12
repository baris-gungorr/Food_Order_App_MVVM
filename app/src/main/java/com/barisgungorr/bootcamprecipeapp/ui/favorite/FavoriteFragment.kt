package com.barisgungorr.bootcamprecipeapp.ui.favorite

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentFavoriteBinding
import com.barisgungorr.bootcamprecipeapp.domain.FavoriteMeal
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels()

    private val adapter: FavoriteAdapter by lazy {
        FavoriteAdapter(
            callbacks = object : FavoriteAdapter.FavoriteCallBack {
                override fun onDeleteFavorite(favorite: FavoriteMeal) {
                    showDeleteFavoriteDialog(favorite)
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observe()
        viewModel.getFavorites()
    }

    private fun observe() {
        viewModel.favoriteList.observe(viewLifecycleOwner) { favorites ->
            val isFavoritesEmpty = favorites.isEmpty()
            binding.ivEmpty.isVisible = isFavoritesEmpty
            binding.tvEmpty.isVisible = isFavoritesEmpty
            adapter.submitList(favorites)
        }
    }

    private fun showDeleteFavoriteDialog(favoriteMeal: FavoriteMeal) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.favorite_page_title)
        builder.setMessage(getString(R.string.favorite_page_delete_tv, favoriteMeal.name))
        builder.setIcon(R.drawable.ic_app_icon)
        builder.setPositiveButton(R.string.favorite_page_yes_tv) { dialog, which ->
            viewModel.deleteFavorite(mealId = favoriteMeal.id)
            dialog.dismiss()
        }
        builder.setNegativeButton(R.string.home_page_button_no) { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun initViews() = with(binding) {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rv.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                layoutManager.orientation
            )
        )
        binding.rv.adapter = adapter
        etSearch.addTextChangedListener {
            viewModel.searchFavorite(it.toString())
        }

        ivHome.setOnClickListener {
            findNavController().navigate(R.id.favoriteToMain)
        }
    }
}