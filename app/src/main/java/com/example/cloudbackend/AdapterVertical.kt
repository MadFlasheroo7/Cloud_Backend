package com.example.cloudbackend

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.rv_vertical.view.*

class AdapterVertical(private var dishes2:List<Dishes>,private val onDishClickListener: OnDishClickListener):RecyclerView.Adapter<AdapterVertical.DishesViewHolder>(){
    inner class DishesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_vertical,parent,false)
        return DishesViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishesViewHolder, position: Int) {
        holder.itemView.apply {
            dishName.text = dishes2[position].title
            val image = dishes2[position].dishimg
            Glide.with(context)
                .asBitmap()
                .load(image)
                .into(dishImage)

            holder.itemView.setOnClickListener {
                onDishClickListener.onDishClickListener(position)
//                val intent = Intent(context,OrderActivity::class.java)
//                intent.putExtra("Position",position)
//                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return dishes2.size
    }
}