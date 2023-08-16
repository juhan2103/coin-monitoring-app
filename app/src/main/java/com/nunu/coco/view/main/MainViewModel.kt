package com.nunu.coco.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nunu.coco.db.entity.InterestCoinEntity
import com.nunu.coco.repository.DbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val dbRepository = DbRepository()

    lateinit var selectedCoinList : LiveData<List<InterestCoinEntity>>

    // CoinListFragment

    fun getAllInterestCoinData() = viewModelScope.launch {

        val coinList = dbRepository.getAllInterestCoinData().asLiveData()
        selectedCoinList = coinList

    }

    fun updateInterestCoinData(interestCoinEntity: InterestCoinEntity) = viewModelScope.launch(Dispatchers.IO) {

        if(interestCoinEntity.selected){
            interestCoinEntity.selected = false
        }
        else{
            interestCoinEntity.selected = true
        }

        dbRepository.updateInterestCoinData(interestCoinEntity)
    }

    // PriceChangeFragment

}