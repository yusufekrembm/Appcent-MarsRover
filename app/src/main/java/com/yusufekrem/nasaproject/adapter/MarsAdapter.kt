package com.yusufekrem.nasaproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yusufekrem.nasaproject.model.Data.Photo
import com.yusufekrem.nasaproject.R
import kotlinx.android.synthetic.main.photo_cell.view.*

class MarsAdapter(private val photos : List<Photo>,
                  private val clickListener: (String)->Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    open class PhotoHolder(itemView : View, clickAtPosition : (Int)->Unit) : RecyclerView.ViewHolder(itemView){
        fun bind(photo : Photo){
            val image = "${photo.img_src}".replace("http","https")
            Glide.with(itemView.context).load(image).into(itemView.roverImage)
            itemView.photos_id.text = photo.camera.name

        }
        init {
            //Listener for Pop Up
            itemView.setOnClickListener {

                clickAtPosition(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PhotoHolder(LayoutInflater.from(parent.context).inflate(R.layout.photo_cell, parent, false)){
            clickListener(photos[it].id.toString())

        }
    }

    override fun getItemCount(): Int {
        return photos.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is PhotoHolder ->{
                holder.bind(photos[position])
            }
        }
    }
}