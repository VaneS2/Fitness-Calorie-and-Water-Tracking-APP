package com.example.fitnessapp.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.Service.MyService
import com.example.fitnessapp.adapter.BasicRecyclerViewAdapter
import com.example.fitnessapp.adapter.WaterRecyclerViewAdapter
import com.example.fitnessapp.database.dao.FirstFragmentMealModel
import com.example.fitnessapp.model.FitnessNews
import com.example.fitnessapp.model.Water
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt
import kotlin.math.roundToLong


class WaterDaily : Fragment() {

    private lateinit var firstFragmentViewModel: FirstFragmentMealModel
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    lateinit var recyclerViewAdapter: WaterRecyclerViewAdapter
    lateinit var waters: MutableList<Double>

    lateinit var recyclerkompomenta: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firstFragmentViewModel= ViewModelProvider(this).get(FirstFragmentMealModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_water_daily, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        waters= mutableListOf();
        recyclerkompomenta = view.findViewById(R.id.waterRecyclerView)

        recyclerkompomenta.layoutManager = LinearLayoutManager(context)

        recyclerViewAdapter= WaterRecyclerViewAdapter(
            this, waters,
            this)

        recyclerkompomenta.adapter=recyclerViewAdapter;

        CoroutineScope(Dispatchers.IO).launch {
            firstFragmentViewModel.loadWater(mAuth.currentUser?.email!!)

            withContext(Dispatchers.Main)
            {
              var izlez1= (firstFragmentViewModel.getWater().waterRem * 10000.0).roundToInt() / 10000.0
                var izlez2=(firstFragmentViewModel.getWater().waterConsumed * 10000.0).roundToInt() / 10000.0

                view.findViewById<TextView>(R.id.remaningW).text=izlez1.toString();
                view.findViewById<TextView>(R.id.consumedW).text=izlez2.toString();

               var niza= firstFragmentViewModel.getWater().cups.split(",");
if(firstFragmentViewModel.getWater().cups!="")
{    for(d in niza)
                {
                    if(d!=""){
                    waters.add(d.toDouble())}
                }

                recyclerViewAdapter.updateData(waters);}
            }

        }
        view.findViewById<AppCompatButton>(R.id.addWater).setOnClickListener()
        {

            var liters=view.findViewById<EditText>(R.id.kolkuMl)

          var v:Water = firstFragmentViewModel.getWater();
            CoroutineScope(Dispatchers.IO).launch {

                if(firstFragmentViewModel.getWater().cups=="") {

                    firstFragmentViewModel.updateWater(
                        liters.text.toString(),
                        v.waterRem - liters.text.toString().toDouble(),
                        v.waterConsumed + liters.text.toString().toDouble(),
                        mAuth.currentUser?.email!!
                    )
                }
                else
                {
                    var niza =
                        firstFragmentViewModel.getWater().cups + "," + liters.text.toString();
                    firstFragmentViewModel.updateWater(
                        niza,
                        v.waterRem - liters.text.toString().toDouble(),
                        v.waterConsumed + liters.text.toString().toDouble(),
                        mAuth.currentUser?.email!!
                    )
                }


                withContext(Dispatchers.Main)
                {
                    findNavController().navigate(R.id.action_waterDaily_self)
                }

            }




        }

        view.findViewById<AppCompatButton>(R.id.clearW).setOnClickListener()
        {


            CoroutineScope(Dispatchers.IO).launch {


                firstFragmentViewModel.updateWater("",firstFragmentViewModel.getWater().waterRem+firstFragmentViewModel.getWater().waterConsumed,0.00,mAuth.currentUser?.email!!)




                withContext(Dispatchers.Main)
                {
                    findNavController().navigate(R.id.action_waterDaily_self)
                }

            }



        }






    }

}