package com.example.api_images.ui

import android.R
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.api_images.client.ApiService
import com.example.api_images.client.Repo
import com.example.api_images.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity() {
    lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitService = ApiService.getInstance()
        val repo = Repo(retrofitService)
        setSupportActionBar(binding.toolbar2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel = ViewModelProvider(this, MyViewModelFactory(repo))[MainViewModel::class.java]
        exampleObserveText()
        viewModel.showFilm("china")
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                onBackPressed()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun exampleObserveText(){
        viewModel.movieList.observe(this,Observer{
            binding.textView2Activity.text = it.toString()

        })
    }
}

