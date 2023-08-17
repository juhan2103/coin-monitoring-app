package com.nunu.coco.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nunu.coco.R
import com.nunu.coco.databinding.FragmentPriceChangeBinding
import com.nunu.coco.view.adapter.PriceListUpDownRvAdapter
import timber.log.Timber

class PriceChangeFragment : Fragment() {

    private val viewModel : MainViewModel by activityViewModels()

    private var _binding : FragmentPriceChangeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPriceChangeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllSelectedCoinData()

        viewModel.arr15min.observe(viewLifecycleOwner, Observer {
            Timber.tag("데이터15분").d(it.toString())

            val priceListUpDownRvAdapter = PriceListUpDownRvAdapter(requireContext(), it)
            binding.price15m.adapter = priceListUpDownRvAdapter
            binding.price15m.layoutManager = LinearLayoutManager(requireContext())
        })

        viewModel.arr30min.observe(viewLifecycleOwner, Observer {
            Timber.tag("데이터30분").d(it.toString())

            val priceListUpDownRvAdapter = PriceListUpDownRvAdapter(requireContext(), it)
            binding.price30m.adapter = priceListUpDownRvAdapter
            binding.price30m.layoutManager = LinearLayoutManager(requireContext())
        })

        viewModel.arr45min.observe(viewLifecycleOwner, Observer {
            Timber.tag("데이터45분").d(it.toString())

            val priceListUpDownRvAdapter = PriceListUpDownRvAdapter(requireContext(), it)
            binding.price45m.adapter = priceListUpDownRvAdapter
            binding.price45m.layoutManager = LinearLayoutManager(requireContext())
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}