package com.example.fitnessapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitnessapp.Fragments.GYM.ExercisesFragment
import com.example.fitnessapp.R
import com.example.fitnessapp.model.Muscles


class MusclesRecyclerViewAdapter(val context: ExercisesFragment, var muscles: MutableList<Muscles>
                                 , val listener: ExercisesFragment
):
    RecyclerView.Adapter<MusclesRecyclerViewAdapter.ViewHolder>() {



   inner class ViewHolder(view: View,): RecyclerView.ViewHolder(view)
    {
        val name: TextView=view.findViewById(R.id.imeNaVezba)
        val image: ImageView=view.findViewById(R.id.imageMuscles)


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_for_muscles, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Muscles = muscles[position]


        holder.name.text =item.name

        holder.itemView.setOnClickListener()
        {
            listener.onItemClick(item.name)
        }

        Glide.with(context).load(item.image).into(holder.image);



    }


    override fun getItemCount(): Int {
        return muscles.size
    }

    fun updateData(data: MutableList<Muscles>) {
        this.muscles = data
        this.notifyDataSetChanged()

    }





}