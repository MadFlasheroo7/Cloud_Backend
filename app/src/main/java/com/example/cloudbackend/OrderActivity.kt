package com.example.cloudbackend

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class OrderActivity: AppCompatActivity() {
    private val userOrderRef = Firebase.firestore.collection("orders")
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
        buyNow.setOnClickListener {
            val dishName = dish_name.text.toString()
            val dishPrice = dish_price.text.toString()
            val orders = Orders(dishName,dishPrice)
            uploadOrders(orders)
            val intent = Intent(this,Sorry::class.java)
            startActivity(intent)
        }
    }
    private fun uploadOrders(orders: Orders) = CoroutineScope(Dispatchers.IO).launch{
        try {
            userOrderRef.add(orders).await()
            withContext(Dispatchers.Main){
                Toast.makeText(this@OrderActivity,"Successful",Toast.LENGTH_LONG).show()
            }
        }catch (e:Exception){
            withContext(Dispatchers.Main){
                Toast.makeText(this@OrderActivity,e.message,Toast.LENGTH_LONG).show()
            }
        }
    }
}