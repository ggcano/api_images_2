package com.example.api_images

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api_images.databinding.ItemListBinding
import com.example.api_images.servicesnew.Photo2

class PhotosAdapter(private val onItemClicked: (positionString: String) -> Unit) :
    RecyclerView.Adapter<MainViewHolder>() {

    var photList = mutableListOf<Photo2>()

    fun setLoompaList(list: List<Photo2>) {
        this.photList = list.toMutableList()
        photList.clear()
        photList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val photoItem = photList[position]
        holder.binding.name.text = photoItem.photographer
        holder.binding.txtId.text = photoItem.id.toString()
        holder.binding.buttonDownload.setOnClickListener {
            val id = photoItem.src2.original
            onItemClicked(id)

        }
        Glide.with(holder.itemView.context).load(photoItem.src2.medium)
            .into(holder.binding.imageview)

    }

    override fun getItemCount(): Int {
        return photList.size
    }
}

class MainViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

}
