package com.example.hard75tracker

import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toolbar

class FullImage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_full_image)
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.R){
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else{@Suppress("DEPRECATION")
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        }
        val imageView=findViewById<ImageView>(R.id.fullImageView)
        val imagePath=intent.getStringExtra("imagePath")
        val bitmap=BitmapFactory.decodeFile(imagePath)
       setupActionBar()
        imageView.setImageBitmap(bitmap)


    }
    private fun setupActionBar(){
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.let {
            it.title=""
        }
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow)
        val back=findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        back.setNavigationOnClickListener{onBackPressed()}
    }
}