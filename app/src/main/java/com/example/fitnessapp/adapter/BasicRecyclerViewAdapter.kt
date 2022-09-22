package com.example.fitnessapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitnessapp.Fragments.News.NewsFragment
import com.example.fitnessapp.R
import com.example.fitnessapp.model.FitnessNews
import pl.droidsonroids.gif.GifImageView


class BasicRecyclerViewAdapter(val context: NewsFragment, var allNews: MutableList<FitnessNews>
                               , val listener: NewsFragment
):
    RecyclerView.Adapter<BasicRecyclerViewAdapter.ViewHolder>() {



   inner class ViewHolder(view: View,): RecyclerView.ViewHolder(view)
    {
        val naslov: TextView=view.findViewById(R.id.titlee)
        val image: ImageView=view.findViewById(R.id.imageView)


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: FitnessNews = allNews[position]


        holder.naslov.text =item.title

        Glide.with(context).load(item.image_url).into(holder.image);

        holder.itemView.setOnClickListener()
        {
            listener.onItemClick(position.toString())
        }

        if(item.image_url==null)
        {
            Glide.with(context).load("https://freeiconshop.com/wp-content/uploads/edd/image-solid.png").into(holder.image);
        }

    }


    override fun getItemCount(): Int {
        return allNews.size
    }

    fun updateData(data: MutableList<FitnessNews>) {
        this.allNews = data
        this.notifyDataSetChanged()

    }





}