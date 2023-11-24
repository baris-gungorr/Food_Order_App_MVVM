package com.barisgungorr.bootcamprecipeapp.ui.order

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.data.entity.Basket
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentOrderBinding
import com.barisgungorr.bootcamprecipeapp.utils.extension.click
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class OrderFragment : Fragment() {
    private lateinit var binding: FragmentOrderBinding
    private val viewModel: OrderViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderBinding.inflate(inflater, container, false)
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
        binding.recyclerView.layoutManager = layoutManager
    }

    private fun observe() {

        lifecycleScope.launchWhenStarted {
            viewModel.message.collectLatest { message ->
                Toast.makeText(context, getString(message), Toast.LENGTH_SHORT).show()
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.navigateMainScreen.collectLatest {
                findNavController().navigate(OrderFragmentDirections.orderToMain())
            }
        }

        viewModel.basketList.observe(viewLifecycleOwner) { baskets ->
            val adapter = OrderAdapter(
                mealList = baskets.orEmpty(),
                callbacks = object : OrderAdapter.OrderCallbacks {
                    override fun onDeleteOrder(basket: Basket) {
                        showDeleteBasketDialog(basket)
                    }

                    override fun onDecreaseOrderQuantity(basket: Basket) {
                        viewModel.decreaseOrderQuantity(basket)
                    }

                    override fun onIncreaseOrderQuantity(basket: Basket) {
                        viewModel.increaseOrderQuantity(basket)
                    }
                }
            )
            binding.recyclerView.adapter = adapter
        }
    }


    private fun showDeleteBasketDialog(basket: Basket) {

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.setTitleAlert)
        builder.setMessage(getString(R.string.are_you_sure_want_to_delete_meal, basket.mealsName))
        builder.setIcon(R.drawable.ic_app_icon)
        builder.setPositiveButton(R.string.yesText) { dialog, which ->

            viewModel.delete(mealId = basket.cardMealsId)
            dialog.dismiss()
        }
        builder.setNegativeButton(R.string.buttonNo) { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }


    private fun initViews() = with(binding) {

        buttonAddCard.click {
            viewModel.completeOrders()
        }
        imageViewBackk?.click {
            findNavController().navigate(R.id.orderToMain)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getBasketMeals()
    }
}


