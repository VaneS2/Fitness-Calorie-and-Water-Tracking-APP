package com.example.fitnessapp.Fragments.Meal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R

class DailyActivityFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val bundle:Bundle= Bundle()
        bundle.putString("goal",arguments?.getString("goal"))

        view.findViewById<Button>(R.id.btOne).setOnClickListener()
        {

            bundle.putString("level","level_1")
            findNavController().navigate(R.id.action_dailyActivityFragment2_to_infoFragment2,bundle)
        }

        view.findViewById<Button>(R.id.btTwo).setOnClickListener()
        {

            bundle.putString("level","level_2")
            findNavController().navigate(R.id.action_dailyActivityFragment2_to_infoFragment2,bundle)
        }

        view.findViewById<Button>(R.id.btThree).setOnClickListener()
        {

            bundle.putString("level","level_3")
            findNavController().navigate(R.id.action_dailyActivityFragment2_to_infoFragment2,bundle)
        }


        view.findViewById<Button>(R.id.btFour).setOnClickListener()
        {

            bundle.putString("level","level_4")
            findNavController().navigate(R.id.action_dailyActivityFragment2_to_infoFragment2,bundle)
        }


        view.findViewById<Button>(R.id.btFive).setOnClickListener()
        {
            val bundle:Bundle= Bundle()
            bundle.putString("level","level_5")
            findNavController().navigate(R.id.action_dailyActivityFragment2_to_infoFragment2,bundle)
        }



    }





}