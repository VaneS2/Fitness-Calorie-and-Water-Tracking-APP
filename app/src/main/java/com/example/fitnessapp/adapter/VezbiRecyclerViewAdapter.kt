package com.example.fitnessapp.adapter

import android.media.AudioRecord.MetricsConstants.SOURCE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.example.fitnessapp.Fragments.GYM.ExercisesFragment
import com.example.fitnessapp.Fragments.GYM.VezbiFragment
import com.example.fitnessapp.R
import com.example.fitnessapp.model.Muscles
import com.example.fitnessapp.model.Vezbii
import pl.droidsonroids.gif.GifImageView


class VezbiRecyclerViewAdapter(val context: VezbiFragment, var muscles: MutableList<Vezbii>
                               , val listener: VezbiFragment
):
    RecyclerView.Adapter<VezbiRecyclerViewAdapter.ViewHolder>() {



   inner class ViewHolder(view: View,): RecyclerView.ViewHolder(view)
    {
        val name: TextView=view.findViewById(R.id.imeEx)
        val image: GifImageView=view.findViewById(R.id.giff)


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_for_vezbi, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Vezbii = muscles[position]


        holder.name.text =item.name

        holder.itemView.setOnClickListener()
        {
            listener.onItemClick(position)
        }

        var n = item.gif.split("")
        var i=2;

        var size=n.size-2
        var url:String=""
        for(la in n)
        {
                if(i==size)
                {
                    break;
                }
                url += n[i];
                i++

        }


       Glide.with(context).load(url).into(holder.image);




    }


    override fun getItemCount(): Int {
        return muscles.size
    }

    fun updateData(data: MutableList<Vezbii>) {
        this.muscles = data
        this.notifyDataSetChanged()

    }





}