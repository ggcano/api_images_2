package com.example.api_images

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api_images.databinding.ItemListBinding
import com.example.api_images.services.Photo
import com.example.api_images.servicesnew.Pexels
import com.example.api_images.servicesnew.Photo2
import okhttp3.internal.notify
import okhttp3.internal.notifyAll

class PhotosAdapter (): RecyclerView.Adapter<MainViewHolder>() {

    var oompaLoompaList = mutableListOf<Photo2>()

    fun setLoompaList(list: List<Photo2>,) {
        this.oompaLoompaList = list.toMutableList()
        oompaLoompaList.clear()
        oompaLoompaList.addAll(list);

       // notifyAll()
        notifyDataSetChanged()



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val loompa = oompaLoompaList[position]
        holder.binding.name.text = loompa.photographer
        holder.binding.txtId.text = loompa.id.toString()
        Glide.with(holder.itemView.context).load(loompa.src2.medium).into(holder.binding.imageview)

    }

    override fun getItemCount(): Int {
        return oompaLoompaList.size
    }
}

class MainViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

}
