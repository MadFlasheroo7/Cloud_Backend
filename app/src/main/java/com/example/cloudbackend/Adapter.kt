package com.example.cloudbackend

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.rv_horizontal.view.*

class Adapter(private var dishes:ArrayList<Dishes>,private val onDishClickListener: OnDishClickListener):RecyclerView.Adapter<Adapter.DishesViewHolder>(){
    inner class DishesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_horizontal,parent,false)
        return DishesViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishesViewHolder, position: Int) {
//        holder.itemView.apply {
            val dish = dishes[position]
            holder.itemView.dishName.text = dish.title
//            dishName.text = dish[position].title
        holder.itemView.apply {
            val image = dishes[position].dishimg
            Glide.with(context)
                .asBitmap()
                .load(image)
                .into(dishImg)

            holder.itemView.setOnClickListener {
                onDishClickListener.onDishClickListener(position)
//                val intent = Intent(context,OrderActivity::class.java)
//                intent.putExtra("Position",position)
//                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return dishes.size
    }
}