package com.example.fitnessapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitnessapp.Fragments.Meal.CaloriesDaily
import com.example.fitnessapp.Fragments.News.NewsFragment
import com.example.fitnessapp.R
import com.example.fitnessapp.model.FitnessNews
import com.example.fitnessapp.model.Product


class ProductRecyclerViewAdapter(val context: CaloriesDaily, var allNews: MutableList<Product>
                                 , val listener: CaloriesDaily
):
    RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder>() {



   inner class ViewHolder(view: View,): RecyclerView.ViewHolder(view)
    {
        val naslov: TextView=view.findViewById(R.id.nameProduct)
        val image: ImageView=view.findViewById(R.id.imageProduct)
        val calories: TextView=view.findViewById(R.id.caloriesProduct)


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.rowproduct, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Product = allNews[position]


        holder.naslov.text =item.name
        holder.calories.text =item.grams +"g " + item.calories + " cal"

        Glide.with(context).load(item.image).into(holder.image);



        if(item.image==null)
        {
            Glide.with(context).load("https://freeiconshop.com/wp-content/uploads/edd/image-solid.png").into(holder.image);
        }

    }


    override fun getItemCount(): Int {
        return allNews.size
    }

    fun updateData(data: MutableList<Product>) {
        this.allNews = data
        this.notifyDataSetChanged()

    }





}