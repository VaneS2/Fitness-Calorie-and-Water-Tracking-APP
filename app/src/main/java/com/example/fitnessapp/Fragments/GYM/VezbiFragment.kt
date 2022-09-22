package com.example.fitnessapp.Fragments.GYM

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitnessapp.R
import com.example.fitnessapp.adapter.MusclesRecyclerViewAdapter
import com.example.fitnessapp.adapter.VezbiRecyclerViewAdapter
import com.example.fitnessapp.model.FitnessNews
import com.example.fitnessapp.model.Muscles
import com.example.fitnessapp.model.Vezbii
import com.google.gson.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import java.io.IOException


class VezbiFragment : Fragment() {
    lateinit var vezbii: MutableList<Vezbii>


    lateinit var recyclerViewAdapter: VezbiRecyclerViewAdapter

    lateinit var recyclerkompomenta: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vezbi, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var koiVezbi= arguments?.getString("muscle");


        val client = OkHttpClient()

        view.findViewById<TextView>(R.id.kojMuskul).text=koiVezbi

        val request: Request = Request.Builder()
            .url("https://exercisedb.p.rapidapi.com/exercises/target/"+koiVezbi)
            .get()
            .addHeader("X-RapidAPI-Key", "b9f880aef6msh084ccf02fc8c55dp1372a1jsn17504890dd8e")
            .addHeader("X-RapidAPI-Host", "exercisedb.p.rapidapi.com")
            .build()

        vezbii = mutableListOf()
        client.newCall(request).enqueue(object: Callback
        {
            override fun onFailure(call: Call, e: IOException) {

                println("Failed")
            }

            override fun onResponse(call: Call, response: Response) {


                var myResponse = response.body?.string()

                val obj: JsonArray? = JsonParser().parse(myResponse).asJsonArray
                var ve: MutableList<Vezbii> = mutableListOf()
                var j = 0;
                if (obj != null) {
                    for (i in obj) {
                        var name: String = obj?.get(j)?.asJsonObject?.get("name").toString()
                        var gif= obj?.get(j)?.asJsonObject?.get("gifUrl")

                        var target: String = obj?.get(j)?.asJsonObject?.get("target").toString()
                        var bodypart: String = obj?.get(j)?.asJsonObject?.get("bodyPart").toString()
                        vezbii.add(Vezbii(name, gif.toString(), bodypart, target))
                        j++
                    }
                }


            }

        })

        Thread.sleep(2000)
        if(vezbii.size<10)
        {
            Thread.sleep(1500)
        }

        recyclerkompomenta = view.findViewById(R.id.listofVezbi)

        recyclerkompomenta.layoutManager = LinearLayoutManager(context)

        recyclerViewAdapter= VezbiRecyclerViewAdapter(
            this, vezbii,
            this)

        recyclerkompomenta.adapter=recyclerViewAdapter;

    }

    fun onItemClick(position: Int) {

        val bundle:Bundle= Bundle()

        var koj:Vezbii=vezbii[position]

        var n = koj.gif.split("")
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

        bundle.putString("name",koj.name)
        bundle.putString("gif",url)
        bundle.putString("target",koj.target)
        bundle.putString("bodyPart",koj.bodyPart)

        findNavController().navigate(R.id.action_vezbiFragment_to_detailsVezbaFragment,bundle)

    }


}