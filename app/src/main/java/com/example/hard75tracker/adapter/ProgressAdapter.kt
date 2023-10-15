package com.example.hard75tracker.adapter

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hard75tracker.FullImage
import com.example.hard75tracker.R

class ProgressAdapter(private val ImageList:List<String>):RecyclerView.Adapter<ProgressAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val imageView:ImageView=itemView.findViewById(R.id.imageProgress)
        val textView:TextView=itemView.findViewById(R.id.dayNumber)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.progress_image_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imagePath=ImageList[position]
        holder.textView.text=imagePath.split("/").last().removeSuffix(".png")

        val bitmap=BitmapFactory.decodeFile(imagePath)
        holder.imageView.setImageBitmap(bitmap)
        holder.imageView.setOnClickListener{
            val intent=Intent(it.context,FullImage::class.java)
            intent.putExtra("imagePath",imagePath)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount()=ImageList.size
}