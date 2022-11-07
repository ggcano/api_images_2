package com.example.api_images

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

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
        viewModel.photoData().observe(this, Observer { src ->
            val floatRateUsd = src.nextPage
            val floatRateGpb = src.photos.map { it.photographer }
            val imageUrl = src.photos.map { it.src.medium}
            Glide.with(this).load(imageUrl[ 0]).into(binding.imageView);

            binding.labelDollar.text = floatRateUsd

            binding.labelLibra.text = floatRateGpb.toString()

        })
    }


}