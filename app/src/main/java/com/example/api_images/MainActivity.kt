package com.example.api_images

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.api_images.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        observeData()
    }

    private fun observeData() {
        viewModel.coinData().observe(this, Observer {
            val floatRateUsd = it.coinBpi.usd.rateFloatDTO
            val floatRateGpb = it.coinBpi.gpb.rateFloatDTO
            val floatRateEur = it.coinBpi.eur.rateFloatDTO


            binding.labelDollar.text = "$floatRateUsd $"
            binding.labelEuro.text = "$floatRateEur €"
            binding.labelLibra.text= "$floatRateGpb €"

        })
    }
}