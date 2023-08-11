package com.nunu.coco.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nunu.coco.MainActivity
import com.nunu.coco.R
import com.nunu.coco.databinding.ActivitySelectBinding
import com.nunu.coco.view.adapter.SelectRvAdapter
import timber.log.Timber

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

        viewModel.setUpFirstFlag()

        binding.laterTextArea.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}