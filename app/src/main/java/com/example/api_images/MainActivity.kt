package com.example.api_images

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
import com.example.api_images.client.ApiService
import com.example.api_images.client.Repo
import com.example.api_images.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var ibitmap: Bitmap

    private val adapterLoompa = PhotosAdapter() { position -> onListItemClick(position) }

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

    private fun adsadasdasda() {
        binding.buttonMoreSearch.setOnClickListener {
            // binding.recyclerPhotos.adapter = adapterLoompa
            viewModel.getAllPhotos(
                "563492ad6f917000010000016ee415c7e5144defbc009b1eae08ca1c",
                caughtStringEditText()
            )

            loadRecyclerView()
        }

    }

 /*   private fun onListItemClick(position: String) {
        val direct: File = File(Environment.getExternalStorageDirectory().toString() + "/DirName")

        if (!direct.exists()) {
            val wallpaperDirectory = File("/sdcard/DirName/")
            wallpaperDirectory.mkdirs()
        }

        val file = File("/sdcard/DirName/", position)
        if (file.exists()) {
            file.delete()
        }
        try {
            val out = FileOutputStream(file)
            ibitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            out.flush()
            out.close()

            Toast.makeText(this, "File saved successfully!",
                Toast.LENGTH_SHORT).show();
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }*/

    private fun onListItemClick(position: String) {
            downloadImage(position)
    }


    private fun downloadImage(imageURL: String) {
        if (!verifyPermissions()!!) {
            return
        }
        val dirPath =
            Environment.getExternalStorageDirectory().absolutePath + "/" + "imagespexel" + "/"
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
                    val bitmap = (resource as BitmapDrawable).bitmap
                    Toast.makeText(this@MainActivity, "Saving Image...", Toast.LENGTH_SHORT).show()
                    saveImage(bitmap, dir, fileName)
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


    private fun verifyPermissions(): Boolean? {

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
        //storageDir.mkdir()
        var successDirCreated = false

        val imageFile = File(storageDir, imageFileName)
        val fOut: OutputStream = FileOutputStream(imageFile)
        if (!storageDir.exists() && successDirCreated) {
            storageDir.mkdir()
        }
       // if (successDirCreated==false ) {

            try {
                if (storageDir.exists()) storageDir.delete()
                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut)
                storageDir.createNewFile()
                fOut.flush()
                fOut.close()

                //fOut.close()
                Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Error while saving image!", Toast.LENGTH_SHORT)
                    .show()
                e.printStackTrace()
            }

    }




    private fun loadRecyclerView() {

                viewModel.photos.observe(this, Observer { responsesss ->
                    if (responsesss !=null) {
                        adapterLoompa.setLoompaList(responsesss)
                    }else{
                        println("error in response de recyclerview")
                    }
                })


    }
    fun saveToExternalStorage() {
        val fullPath = Environment.getExternalStorageDirectory().absolutePath + "/directoryName"
        try {
            val dir = File(fullPath)
            if (!dir.exists()) {
                dir.mkdirs()
            }
            var fOut: OutputStream? = null
            val file = File(fullPath, "fileName.jpg")
            if (file.exists()) file.delete()
            file.createNewFile()
            fOut = FileOutputStream(file)
            fOut.flush()
            fOut.close()
        } catch (e: java.lang.Exception) {
            Toast.makeText(this, "Failed to make folder!", Toast.LENGTH_SHORT).show()
        }
    }

/*
  private fun onListItemClick(position: String) {

        try {
            val filePath = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                position
            )
            val outputStream: OutputStream = FileOutputStream(filePath)
            ibitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
            Toast.makeText(
                this@MainActivity,
                position + "Sucessfully saved in Download Folder",
                Toast.LENGTH_SHORT
            ).show()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
*/


    private fun caughtStringEditText():String {
        return binding.searchTxt.text.toString()
    }


    private fun setupRecyclerView(){
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerPhotos.layoutManager = layoutManager

    }

}