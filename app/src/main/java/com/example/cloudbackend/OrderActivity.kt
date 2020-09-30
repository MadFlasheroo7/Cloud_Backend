package com.example.cloudbackend

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        val price = intent.getStringExtra("DISH_PRICE")
        dish_price.text = price
        val title = intent.getStringExtra("DISH_TITLE")
        dish_name.text = title
        val image = intent.getIntExtra("DISH_IMAGE",0)
        Glide.with(this)
                .asBitmap()
                .load(image)
                .into(dishImage)
    }
}