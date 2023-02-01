package com.example.api_images.ui

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.api_images.PhotosAdapter
import com.example.api_images.R
import com.example.api_images.client.ApiService
import com.example.api_images.client.Repo
import com.example.api_images.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    private val adapterPhotos = PhotosAdapter() { position -> onListItemClick(position) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val retrofitService = ApiService.getInstance()
        val repo = Repo(retrofitService)
        binding.recyclerPhotos.adapter = adapterPhotos
        viewModel = ViewModelProvider(this, MyViewModelFactory(repo))[MainViewModel::class.java]

        setupRecyclerView()
        setupSearchMorePhotos()

    }



    private fun setupSearchMorePhotos() {
        binding.buttonMoreSearch.setOnClickListener {
            // binding.recyclerPhotos.adapter = adapterLoompa
            viewModel.getAllPhotos(
                caughtStringEditText()
            )
            loadRecyclerView()
            exampleObserveText()
            viewModel.showFilm(caughtStringEditText())
        }


    }

    private fun onListItemClick(position: String) {
        downloadImage(position)
        Glide.with(this)
            .load(R.drawable.descargajpg)
            .into(binding.imageView)
        binding.photographer.text = "Descarga completada"
    }


    private fun downloadImage(imageURL: String) {
        if (!verifyPermissions()) {
            return
        }
        val dirPath =
            Environment.getExternalStorageDirectory().absolutePath + "/" + "Download" + "/"
        val dir = File(dirPath)
        val fileName = imageURL.substring(imageURL.lastIndexOf('/') + 1)
        Glide.with(this)
            .load(imageURL)
            // .into(binding.imageView)
            .into(object : CustomTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    @Nullable transition: Transition<in Drawable?>?
                ) {
                    try {
                        val bitmap = (resource as BitmapDrawable).bitmap
                        Toast.makeText(this@MainActivity, "Saving Image...", Toast.LENGTH_SHORT)
                            .show()
                        saveImage(bitmap, dir, fileName)
                    } catch (e: Exception) { // Toast.makeText(this, "Error while saving image!", Toast.LENGTH_SHORT)
                        e.printStackTrace()
                    }
                }


                override fun onLoadCleared(@Nullable placeholder: Drawable?) {}
                override fun onLoadFailed(@Nullable errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    Toast.makeText(
                        this@MainActivity,
                        "Failed to Download Image! Please try again later.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }


    private fun verifyPermissions(): Boolean {
        // This will return the current Status
        val permissionExternalMemory =
            ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (permissionExternalMemory != PackageManager.PERMISSION_GRANTED) {
            val STORAGE_PERMISSIONS = arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            // If permission not granted then ask for permission real time.
            ActivityCompat.requestPermissions(this, STORAGE_PERMISSIONS, 1)
            return false
        }
        return true
    }

    private fun saveImage(image: Bitmap, storageDir: File, imageFileName: String) {
        storageDir.mkdir()

        val imageFile = File(storageDir, imageFileName)
        val fOut: OutputStream = FileOutputStream(imageFile)

        try {

            image.compress(Bitmap.CompressFormat.JPEG, 100, fOut)
            storageDir.createNewFile()
            fOut.flush()
            fOut.close()

            Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Error while saving image!", Toast.LENGTH_SHORT)
                .show()
            e.printStackTrace()
        }
    }


    private fun loadRecyclerView() {
        viewModel.photos.observe(this, Observer { response ->
            if (response != null) {
                adapterPhotos.setLoompaList(response)
            } else {
                println("error in response de recyclerview")
            }
        })


    }

    private fun exampleObserveText(){
        viewModel.movieList.observe(this,Observer{
            binding.photographer.text = it.toString()

        })
    }


    private fun caughtStringEditText(): String {
        return binding.searchTxt.text.toString()
    }


    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerPhotos.layoutManager = layoutManager

    }

}