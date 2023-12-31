package com.nunu.coco.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.nunu.coco.background.GetCoinPriceRecentContractedWorkManager
import com.nunu.coco.view.main.MainActivity
import com.nunu.coco.databinding.ActivitySelectBinding
import com.nunu.coco.view.adapter.SelectRvAdapter
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SelectActivity : AppCompatActivity() {

    private val viewModel : SelectViewModel by viewModels()

    private lateinit var selectRvAdapter: SelectRvAdapter

    private lateinit var binding : ActivitySelectBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getCurrentCoinList()
        viewModel.currentPriceResult.observe(this, Observer {

            selectRvAdapter = SelectRvAdapter(this, it)

            binding.coinListRV.adapter = selectRvAdapter
            binding.coinListRV.layoutManager = LinearLayoutManager(this)

            Timber.d(it.toString())
        })

        binding.laterTextArea.setOnClickListener {

            viewModel.setUpFirstFlag()
            viewModel.saveSelectedCoinList(selectRvAdapter.selectedCoinList)

        }

        viewModel.save.observe(this, Observer {
            if(it.equals("done")){

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                // 가장 처음으로 저장한 코인 정보가 저장되는 시점
                saveInterestCoinDataPeriodic()
            }
        })

    }

    private fun saveInterestCoinDataPeriodic(){

        val myWork = PeriodicWorkRequest.Builder(
            GetCoinPriceRecentContractedWorkManager::class.java,
            15,
            TimeUnit.MINUTES
        ).build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "GetCoinPriceRecentContractedWorkManger",
            ExistingPeriodicWorkPolicy.KEEP,
            myWork
        )
    }
}