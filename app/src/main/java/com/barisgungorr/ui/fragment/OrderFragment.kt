package com.barisgungorr.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentOrderBinding
import com.barisgungorr.ui.adapter.OrderAdapter
import com.barisgungorr.ui.viewmodel.MainViewModel
import com.barisgungorr.ui.viewmodel.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class OrderFragment : Fragment() {
    private lateinit var binding: FragmentOrderBinding
    private val viewModel: OrderViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentOrderBinding.inflate(inflater, container, false)
        val layoutManager = LinearLayoutManager(requireContext())

        binding.recyclerView.layoutManager = layoutManager


        viewModel.basketList.observe(viewLifecycleOwner) {
            val adapter = it?.let { it1 -> OrderAdapter(requireContext(), it1, viewModel) }

                    binding.recyclerView.adapter = adapter
                    viewModel.orderTotalPrice()
                   viewModel.orderTotalPrice()

                }

            binding.buttonAddCard.setOnClickListener {
                val isBasketEmpty =viewModel.basketList.value == null ||viewModel.basketList.value?.isEmpty() == true
                if (isBasketEmpty) {
                    Toast.makeText(requireContext(), "Please add product to cart!", Toast.LENGTH_LONG).show()
                } else {

                    binding.completeImage.setImageResource(R.drawable.r)
                    binding.completeImage.visibility = View.VISIBLE

                    CoroutineScope(Dispatchers.Main).launch {
                        delay(3000)

                        Navigation.findNavController(it).navigate(R.id.orderToMain)

                }
                }

              viewModel.clearBasket()
            }

        binding.imageViewBackk?.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.orderToMain)

        }

            return binding.root

        }

    override fun onResume() {
        super.onResume()
        viewModel.getOrder()
    }

}

