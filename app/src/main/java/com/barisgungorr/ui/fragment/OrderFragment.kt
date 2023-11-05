package com.barisgungorr.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentOrderBinding
import com.barisgungorr.ui.adapter.OrderAdapter
import com.barisgungorr.ui.viewmodel.OrderViewModel
import com.barisgungorr.utils.extension.click
import com.barisgungorr.utils.extension.transition
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


        viewModel.basketList.observe(viewLifecycleOwner) { sepetler ->
            val adapter = sepetler?.let { it1 -> OrderAdapter(it1, viewModel) }

            binding.recyclerView.adapter = adapter
            viewModel.orderTotalPrice()
        }

        binding.buttonAddCard.click {

            val isBasketEmpty =
                viewModel.basketList.value == null || viewModel.basketList.value?.isEmpty() == true
            if (isBasketEmpty) {
                Toast.makeText(requireContext(), R.string.addProductCard, Toast.LENGTH_LONG).show()
            } else {
                binding.completeImage.setImageResource(R.drawable.r)
                binding.completeImage.visibility = View.VISIBLE

                CoroutineScope(Dispatchers.Main).launch {
                    delay(3000)

                    findNavController().navigate(R.id.orderToMain)
                }
            }

            viewModel.clearBasket()
        }

        binding.imageViewBackk?.click {
            this.view?.let { Navigation.transition(it, R.id.orderToMain) }

        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getOrder()
    }
}


