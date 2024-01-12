package com.barisgungorr.bootcamprecipeapp.ui.card

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.BasketMealResponse
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentCardBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class CardFragment : Fragment() {
    private lateinit var binding: FragmentCardBinding
    private val viewModel: CardViewModel by viewModels()

    private val adapter by lazy {
        CardAdapter(
            callbacks = object : CardAdapter.OrderCallbacks {
                override fun onDeleteOrder(basket: BasketMealResponse) {
                    showDeleteBasketDialog(basket)
                }

                override fun onDecreaseOrderQuantity(basket: BasketMealResponse) {
                    viewModel.decreaseOrderQuantity(basket)
                }

                override fun onIncreaseOrderQuantity(basket: BasketMealResponse) {
                    viewModel.increaseOrderQuantity(basket)
                }
            }
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        initViews()
        viewModel.getBasketMeals()
    }

    private fun observe() {
        binding.rv.adapter = adapter
        viewModel.basketList.observe(viewLifecycleOwner) { baskets ->
            adapter.submitList(baskets.orEmpty())
        }
    }
    private fun initViews() {
        with(binding) {
            val layoutManager = LinearLayoutManager(requireContext())
            rv.layoutManager = layoutManager

            btnAddCard.setOnClickListener {
                viewModel.completeOrders()
            }
            ivBack.setOnClickListener {
                findNavController().navigate(R.id.orderToMain)
            }
        }
    }

    private fun showDeleteBasketDialog(basket: BasketMealResponse) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.card_page_title)
        builder.setMessage(getString(R.string.favorite_page_delete_tv, basket.name))
        builder.setIcon(R.drawable.ic_app_icon)
        builder.setPositiveButton(R.string.favorite_page_yes_tv) { dialog, which ->
            viewModel.delete(mealId = basket.id)
            dialog.dismiss()
        }
        builder.setNegativeButton(R.string.home_page_button_no) { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }
}


