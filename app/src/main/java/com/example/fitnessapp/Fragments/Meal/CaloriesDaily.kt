package com.example.fitnessapp.Fragments.Meal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.adapter.BasicRecyclerViewAdapter
import com.example.fitnessapp.adapter.ProductRecyclerViewAdapter
import com.example.fitnessapp.database.dao.FirstFragmentMealModel
import com.example.fitnessapp.databinding.FragmentFirstBinding
import com.example.fitnessapp.model.FitnessNews
import com.example.fitnessapp.model.Product
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*
import kotlin.random.Random


class CaloriesDaily : Fragment() {

    private lateinit var firstFragmentViewModel: FirstFragmentMealModel

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()


    lateinit var allNews: MutableList<Product>
    lateinit var recyclerViewAdapter: ProductRecyclerViewAdapter


    lateinit var recyclerkompomenta: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        firstFragmentViewModel= ViewModelProvider(this).get(FirstFragmentMealModel::class.java)



        return inflater.inflate(R.layout.fragment_calories_daily, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        CoroutineScope(Dispatchers.IO).launch {
            firstFragmentViewModel.loadwithProducts(mAuth.currentUser?.email!!)

            withContext(Dispatchers.Main)
            {
                view.findViewById<TextView>(R.id.program).text=firstFragmentViewModel.getwithProducts().playList.status.uppercase() + " WEIGHT";
                view.findViewById<TextView>(R.id.remaning).text=firstFragmentViewModel.getwithProducts().playList.caloriesRem;
                view.findViewById<TextView>(R.id.consumed).text=firstFragmentViewModel.getwithProducts().playList.calories;
            }

        }


        view.findViewById<AppCompatButton>(R.id.clear).setOnClickListener()
        {


            CoroutineScope(Dispatchers.IO).launch {
                firstFragmentViewModel.loadwithProducts(mAuth.currentUser?.email!!);

                firstFragmentViewModel.updateMealUser(
                    ("0").toString(),
                    (firstFragmentViewModel.getwithProducts().playList.caloriesRem.toDouble() +
                            firstFragmentViewModel.getwithProducts().playList.calories.toDouble()).toString(),
                    mAuth.currentUser?.email!!
                )

                firstFragmentViewModel.brisiProduct(mAuth.currentUser?.email!!);


                withContext(Dispatchers.Main)
                {
                    findNavController().navigate(R.id.action_caloriesDaily_self)
                }

            }



        }





        view.findViewById<AppCompatButton>(R.id.addFood).setOnClickListener()
        {

            /*var p:Product=Product(2,"Product","543","100","https://upload.wikimedia.org/wikipedia/commons/thumb/a/a3/Eq_it-na_pizza-margherita_sep2005_sml.jpg/800px-Eq_it-na_pizza-margherita_sep2005_sml.jpg",
                mAuth.currentUser?.email!!)


            CoroutineScope(Dispatchers.IO).launch {
                firstFragmentViewModel.dodadiProduct(p);

                firstFragmentViewModel.updateMealUser(
                    (firstFragmentViewModel.getwithProducts().playList.calories.toDouble()+p.calories.toDouble()).toString(),
                    (firstFragmentViewModel.getwithProducts().playList.caloriesRem.toDouble()-p.calories.toDouble()).toString()
                    , mAuth.currentUser?.email!!
                )




            }*/

                findNavController().navigate(R.id.action_caloriesDaily_to_addProductFragment2)

        }


        view.findViewById<AppCompatButton>(R.id.waterButton).setOnClickListener()
        {
            findNavController().navigate(R.id.action_caloriesDaily_to_waterDaily)
        }


        allNews= mutableListOf();
        recyclerkompomenta = view.findViewById(R.id.recyclerViewProducts)

        recyclerkompomenta.layoutManager = LinearLayoutManager(context)

        recyclerViewAdapter= ProductRecyclerViewAdapter(
            this, allNews,
            this)

        recyclerkompomenta.adapter=recyclerViewAdapter;


        CoroutineScope(Dispatchers.IO).launch {


            firstFragmentViewModel.loadwithProducts(mAuth.currentUser?.email!!)

            withContext(Dispatchers.Main)
            {
                recyclerViewAdapter.updateData(firstFragmentViewModel.getwithProducts().products)
            }

        }







    }



}