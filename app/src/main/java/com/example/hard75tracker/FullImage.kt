package com.example.hard75tracker

import android.graphics.BitmapFactory
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.widget.ImageView

class FullImage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_full_image)
        val imageView=findViewById<ImageView>(R.id.fullImageView)
        val imagePath=intent.getStringExtra("imagePath")
        val bitmap=BitmapFactory.decodeFile(imagePath)
        imageView.setImageBitmap(bitmap)


    }
}