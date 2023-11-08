package com.barisgungorr.bootcamprecipeapp.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentOrderBinding
import com.barisgungorr.bootcamprecipeapp.utils.extension.click
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
        viewModel.basketList.observe(viewLifecycleOwner) { sepetler ->
            val adapter = sepetler?.let { it1 -> OrderAdapter(it1, viewModel) }

            binding.recyclerView.adapter = adapter
            viewModel.orderTotalPrice()
        }
    }
    private fun initViews() = with(binding) {

        buttonAddCard.click {

            val isBasketEmpty =
                viewModel.basketList.value == null || viewModel.basketList.value?.isEmpty() == true
            if (isBasketEmpty) {
                Toast.makeText(requireContext(), R.string.addProductCard, Toast.LENGTH_LONG).show()
            } else {
                binding.completeImage.setImageResource(R.drawable.r)
                binding.completeImage.isVisible = true

                CoroutineScope(Dispatchers.Main).launch {
                    delay(3000)

                    findNavController().navigate(R.id.orderToMain)
                }
            }

            viewModel.clearBasket()
        }

        binding.imageViewBackk?.click {
            findNavController().navigate(R.id.orderToMain)

        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getOrder()
    }
}


