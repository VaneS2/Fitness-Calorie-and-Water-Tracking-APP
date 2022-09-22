package com.example.fitnessapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitnessapp.Fragments.News.NewsFragment
import com.example.fitnessapp.Fragments.WaterDaily
import com.example.fitnessapp.R
import com.example.fitnessapp.model.FitnessNews
import pl.droidsonroids.gif.GifImageView


class WaterRecyclerViewAdapter(val context: WaterDaily, var allNews: MutableList<Double>
                               , val listener: WaterDaily
):
    RecyclerView.Adapter<WaterRecyclerViewAdapter.ViewHolder>() {



   inner class ViewHolder(view: View,): RecyclerView.ViewHolder(view)
    {
        val naslov: TextView=view.findViewById(R.id.litersA)
        val image: ImageView=view.findViewById(R.id.imageView444)


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_for_water, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Double = allNews[position]


        holder.naslov.text =item.toString() + "liters";


    }


    override fun getItemCount(): Int {
        return allNews.size
    }

    fun updateData(data: MutableList<Double>) {
        this.allNews = data
        this.notifyDataSetChanged()

    }





}