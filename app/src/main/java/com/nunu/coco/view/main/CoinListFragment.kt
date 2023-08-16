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
import com.nunu.coco.databinding.FragmentCoinListBinding
import com.nunu.coco.db.entity.InterestCoinEntity
import com.nunu.coco.view.adapter.CoinListRvAdapter
import timber.log.Timber

class CoinListFragment : Fragment() {

    private val viewModel : MainViewModel by activityViewModels()

    private var _binding : FragmentCoinListBinding? = null
    private val binding get() = _binding!!

    private val selectedList = ArrayList<InterestCoinEntity>()
    private val unSelectedList = ArrayList<InterestCoinEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCoinListBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllInterestCoinData()
        viewModel.selectedCoinList.observe(viewLifecycleOwner, Observer {

            selectedList.clear()
            unSelectedList.clear()

            for(item in it){

                if(item.selected){
                    selectedList.add(item)
                }
                else{
                    unSelectedList.add(item)
                }

            }

            Timber.d(selectedList.toString())
            Timber.d(unSelectedList.toString())

            setSelectedListRv()

        })
    }

    private fun setSelectedListRv(){

        val selectedRvAdapter = CoinListRvAdapter(requireContext(), selectedList)
        binding.selectedCoinRV.adapter = selectedRvAdapter
        binding.selectedCoinRV.layoutManager = LinearLayoutManager(requireContext())

        selectedRvAdapter.itemClick = object : CoinListRvAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {

                viewModel.updateInterestCoinData(selectedList[position])

            }
        }

        val unSelectedRvAdapter = CoinListRvAdapter(requireContext(), unSelectedList)
        binding.unSelectedCoinRV.adapter = unSelectedRvAdapter
        binding.unSelectedCoinRV.layoutManager = LinearLayoutManager(requireContext())

        unSelectedRvAdapter.itemClick = object : CoinListRvAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {

                viewModel.updateInterestCoinData(unSelectedList[position])

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}