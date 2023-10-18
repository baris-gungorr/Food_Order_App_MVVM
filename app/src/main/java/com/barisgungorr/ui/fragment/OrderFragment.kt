package com.barisgungorr.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentOrderBinding
import com.barisgungorr.ui.adapter.HomeCardAdapter
import com.barisgungorr.ui.adapter.OrderAdapter
import com.barisgungorr.ui.viewmodel.OrderViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class OrderFragment : Fragment() {
    private lateinit var binding: FragmentOrderBinding
    private lateinit var viewModel: OrderViewModel


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



            binding.apply {
                if (binding.recyclerView.adapter != null &&
                    binding.recyclerView.adapter?.itemCount != null) {


                    binding.recyclerView.adapter = adapter
                    viewModel.orderTotalPrice()
                    updatePrice()
                }
            }
        }


            binding.buttonAddCard.setOnClickListener {

                binding.completeImage.setImageResource(R.drawable.r)
                binding.completeImage.visibility = View.VISIBLE

                CoroutineScope(Dispatchers.Main).launch {
                    delay(3000)
                    Navigation.findNavController(it).navigate(R.id.orderToMain)
                }

                clearBasket()
            }



            return binding.root

        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val tempViewModel: OrderViewModel by viewModels()
            viewModel = tempViewModel

        }

    override fun onResume() {
        super.onResume()
        viewModel.getOrder()
    }
    fun updatePrice(){
        viewModel.orderTotalPrice().toInt()

    }

    fun clearBasket() {

        viewModel.clearBasket()
    }

}

