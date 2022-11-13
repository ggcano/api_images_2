package com.example.api_images

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.api_images.client.ApiService
import com.example.api_images.client.Repo

import com.example.api_images.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val adapterLoompa = PhotosAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val retrofitService = ApiService.getInstance()
        val repo = Repo(retrofitService)
        binding.recyclerPhotos.adapter = adapterLoompa
        viewModel = ViewModelProvider(this, MyViewModelFactory(repo))[MainViewModel::class.java]
        observeData()

        setupRecyclerView()
      adsadasdasda()
    }

    private fun observeData() {
        binding.buttonSearch.setOnClickListener {
            viewModel.getCustomPost(
                "563492ad6f917000010000016ee415c7e5144defbc009b1eae08ca1c",
                caughtStringEditText()
            )

            viewModel.responseListMLD.observe(this, Observer { response ->
                if (response!!.isSuccessful) {
                    val body = response.body()


                    binding.photographer.text =
                        response.body()?.photos?.get(0)?.photographer.toString()
                    Glide.with(this).load(body?.photos?.get(0)?.src?.medium.toString())
                        .into(binding.imageView)

                } else {
                    println(("Error in response from retrofit in MainActivity"))
                    Toast.makeText(this, "Error en Sevicio", Toast.LENGTH_SHORT).show()
                }
            })
        }


    }

    private fun adsadasdasda (){
        binding.buttonMoreSearch.setOnClickListener {
           // binding.recyclerPhotos.adapter = adapterLoompa
            viewModel.getAllMovies(
                "563492ad6f917000010000016ee415c7e5144defbc009b1eae08ca1c",
                caughtStringEditText()
            )
            loadRecyclerView()
        }

    }


    private fun loadRecyclerView() {

                viewModel.responseListPhotos.observe(this, Observer { responsesss ->
                    if (responsesss !=null) {
                        adapterLoompa.setLoompaList(responsesss)
                    }else{
                        println("error in response de recyclerview")
                    }
                })


    }

    private fun caughtStringEditText():String {
        return binding.searchTxt.text.toString()
    }
    private fun onListItemClick(position: Int) {/*
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE,position.toString())
        }
        startActivity(intent)*/
    }

    private fun setupRecyclerView(){
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerPhotos.layoutManager = layoutManager

    }

}