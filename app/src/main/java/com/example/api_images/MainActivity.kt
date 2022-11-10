package com.example.api_images

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.api_images.client.ApiService
import com.example.api_images.client.Repo

import com.example.api_images.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val retrofitService = ApiService.getInstance()
        val repo = Repo(retrofitService)
        viewModel = ViewModelProvider(this, MyViewModelFactory(repo))[MainViewModel::class.java]
        observeData()
    }

    private fun observeData() {
        viewModel.getCustomPost("563492ad6f917000010000016ee415c7e5144defbc009b1eae08ca1c")

        viewModel.responseListMLD.observe(this, Observer { src ->
            if (src!!.isSuccessful) {
                val cuerpo = src.body()
                val nexPage=  src.body()?.nextPage
                binding.labelDollar.text = nexPage
                binding.labelLibra.text = src.body()?.photos?.get(0)?.photographer.toString()
                Glide.with(this).load(cuerpo?.photos?.get(0)?.src?.medium.toString())
                 .into(binding.imageView)

            }else{
                println(("Error in response from retrofit in MainActivity"))
            }
         /*   val nexPage = src.
            val photographer = src.photos.map { it.photographer }
            val imageUrl = src.photos.map { it.src.medium}
            Glide.with(this).load(imageUrl[ 0]).into(binding.imageView)

            binding.labelDollar.text = nexPage

            binding.labelLibra.text = photographer.toString()*/

        })
    }


    private fun searchQuery():String{
        binding.searchTxt.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                TODO("Not yet implemented")
            }

        })
        return String()
    }
}