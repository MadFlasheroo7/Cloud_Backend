package com.example.cloudbackend

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
    val title = intent.getStringExtra("DISH_TITLE")
    dish_name.text = title
    val image = intent.getIntExtra("DISH_IMAGE",0)
    Glide.with(this)
            .asBitmap()
            .load(image)
            .into(dishImage)
    }
}