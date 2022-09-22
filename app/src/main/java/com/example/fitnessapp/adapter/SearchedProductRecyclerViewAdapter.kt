package com.example.fitnessapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitnessapp.Fragments.Meal.AddProductFragment
import com.example.fitnessapp.Fragments.Meal.CaloriesDaily
import com.example.fitnessapp.Fragments.News.NewsFragment
import com.example.fitnessapp.R
import com.example.fitnessapp.model.FitnessNews
import com.example.fitnessapp.model.Product
import com.example.fitnessapp.model.ProductSearched


class SearchedProductRecyclerViewAdapter(val context: AddProductFragment, var allNews: MutableList<ProductSearched>
                                         , val listener: AddProductFragment
):
    RecyclerView.Adapter<SearchedProductRecyclerViewAdapter.ViewHolder>() {



   inner class ViewHolder(view: View,): RecyclerView.ViewHolder(view)
    {
        val naslov: TextView=view.findViewById(R.id.searchedNameProduct)
        val image: ImageView=view.findViewById(R.id.searchedImageProduct)
        val calories: TextView=view.findViewById(R.id.searchedCaloriesProduct)


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_searched_products, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: ProductSearched = allNews[position]


        holder.naslov.text =item.name
        holder.calories.text =item.calories + " cal"



       holder.itemView.setOnClickListener()
        {
            listener.onItemClick2(position.toString())
        }

        if(item.image=="")
        {
            Glide.with(context).load("https://freeiconshop.com/wp-content/uploads/edd/image-solid.png").into(holder.image);
        }else {
            Glide.with(context).load(item.image).into(holder.image);
        }

    }


    override fun getItemCount(): Int {
        return allNews.size
    }

    fun updateData(data: MutableList<ProductSearched>) {
        this.allNews = data
        this.notifyDataSetChanged()

    }





}