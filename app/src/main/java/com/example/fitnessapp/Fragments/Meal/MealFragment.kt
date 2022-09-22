package com.example.fitnessapp.Fragments.Meal

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.database.dao.FirstFragmentMealModel
import com.example.fitnessapp.databinding.FragmentSecondBinding
import com.example.fitnessapp.model.FitnessNews
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MealFragment : Fragment() {

    private lateinit var firstFragmentViewModel: FirstFragmentMealModel

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_second, container, false)



        firstFragmentViewModel= ViewModelProvider(this).get(FirstFragmentMealModel::class.java)




        var gainButton=view.findViewById<ImageButton>(R.id.gainWeight)
        gainButton.setOnClickListener()
        {
            val bundle:Bundle= Bundle()
            bundle.putString("goal","gain")
            findNavController().navigate(R.id.action_mealFragment_to_dailyActivityFragment2,bundle)

        }

        var loseButton=view.findViewById<ImageButton>(R.id.loseWeight)
        loseButton.setOnClickListener()
        {
            val bundle:Bundle= Bundle()
            bundle.putString("goal","lose")
            findNavController().navigate(R.id.action_mealFragment_to_dailyActivityFragment2,bundle)
        }


        var maintainButton=view.findViewById<ImageButton>(R.id.maintainWeight)
        maintainButton.setOnClickListener()
        {
            val bundle:Bundle= Bundle()
            bundle.putString("goal","maintain")
            findNavController().navigate(R.id.action_mealFragment_to_dailyActivityFragment2,bundle)
        }

        return view;

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


}