package com.example.gifviewer.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.gifviewer.R

class FullScreenGif : AppCompatActivity() {

    private var gifUrl: String = "";
    private lateinit var fullScreenImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_gif)
        val bundle: Bundle? = intent.extras
        gifUrl = bundle?.getString("gif_url").toString()
        fullScreenImageView = findViewById(R.id.fullScreenImageView)
        Glide.with(this)
            .load(gifUrl)
            .into(fullScreenImageView)
    }
}
