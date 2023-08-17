package com.nunu.coco.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.nunu.coco.R
import com.nunu.coco.databinding.ActivityMainBinding
import com.nunu.coco.view.setting.SettingActivity
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        Timber.d("onCreate")

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.setting.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        val bottomNavigationView = binding.bottomNavigationView
        val navController = findNavController(R.id.fragmentContainerView)

        bottomNavigationView.setupWithNavController(navController)

    }
}