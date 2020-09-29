package com.example.cloudbackend

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Home : Fragment(),OnDishClickListener {
    var dishlist = ArrayList<Dishes>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        dishlist.add(Dishes("Chicken Tikka :)",R.drawable.chicken_tikka))
        dishlist.add(Dishes("chicken Noodles",R.drawable.chicken_noodles))
        dishlist.add(Dishes("KFC wala chicken",R.drawable.best_chicken))
        dishlist.add(Dishes("MacDonald wala chicken",R.drawable.best_chicken))
        dishlist.add(Dishes("chicken Noodles",R.drawable.chicken_noodles))
        dishlist.add(Dishes("Full Thali",R.drawable.veg2))
        dishlist.add(Dishes("chicken Noodles",R.drawable.chicken_noodles))
        dishlist.add(Dishes("Another Chicken",R.drawable.best_chicken))

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvHorizontal)
        recyclerView.setHasFixedSize(true)
        val adapter = Adapter(dishlist,this)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
        recyclerView.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)


        val recyclerView2 = view.findViewById<RecyclerView>(R.id.recyclerViewVertical)
        recyclerView2.setHasFixedSize(true)
        val adapter2 = AdapterVertical(dishlist,this)
        recyclerView2.adapter = adapter2
        adapter2.notifyDataSetChanged()
        recyclerView2.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,true)
        return view
    }

    override fun onDishClickListener(position: Int) {
        val intent = Intent(context,OrderActivity::class.java)
        intent.putExtra("DISH_TITLE",dishlist[position].title)
        intent.putExtra("DISH_IMAGE",dishlist[position].dishimg)
        startActivity(intent)
    }
}