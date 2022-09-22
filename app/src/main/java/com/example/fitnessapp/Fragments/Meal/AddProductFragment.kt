package com.example.fitnessapp.Fragments.Meal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.adapter.SearchedProductRecyclerViewAdapter
import com.example.fitnessapp.database.dao.FirstFragmentMealModel
import com.example.fitnessapp.model.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_add_product.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AddProductFragment : Fragment() {


     lateinit var firstFragmentViewModel: FirstFragmentMealModel
     lateinit var product:Product
     val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    lateinit var searchedProducts:MutableList<ProductSearched>

    lateinit var allNews: MutableList<Product>
    lateinit var recyclerViewAdapter: SearchedProductRecyclerViewAdapter


    lateinit var recyclerkompomenta: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        firstFragmentViewModel= ViewModelProvider(this).get(FirstFragmentMealModel::class.java)
        return inflater.inflate(R.layout.fragment_add_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        allNews= mutableListOf();
        searchedProducts= mutableListOf();
        recyclerkompomenta = view.findViewById(R.id.searchedP)

        recyclerkompomenta.layoutManager = LinearLayoutManager(context)

        recyclerViewAdapter= SearchedProductRecyclerViewAdapter(
            this, searchedProducts,
            this)

        recyclerkompomenta.adapter=recyclerViewAdapter;


        kopcheSearch.setOnClickListener()
        {
            var ap= Retrofit.Builder()
                .baseUrl(FoodApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            var API=ap.create(FoodApi::class.java)
            var prebarano=view.findViewById<EditText>(R.id.searchProduct);
            var call=API.get(prebarano.text.toString())

            call?.enqueue(object: retrofit2.Callback<Final> {
                override fun onResponse(call: Call<Final>?, response: Response<Final>?) {
                    val novosti:Final = response?.body() as Final
var i=0;
                    if (novosti != null) {
                        searchedProducts= mutableListOf();

                        for (item in novosti.hints) {
                            if(item.food.image!=null){
                            searchedProducts.add(ProductSearched(i,item.food.label,item.food.nutrients.ENERC_KCAL,"100",item.food.image))
                                }
                            else
                            {
                                searchedProducts.add(ProductSearched(i,item.food.label,item.food.nutrients.ENERC_KCAL,"100",""))
                            }
                            i++;
                        }


                         recyclerViewAdapter.updateData(searchedProducts)
                        println("USPESNOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO")
                    }
                }

                override fun onFailure(call: Call<Final>?, t: Throwable?) {
                    TODO("Not yet implemented")
                }


            })

        }
    }


    fun onItemClick(position: String) {
      var added=searchedProducts[position.toInt()]
        var calories=""+added.calories
        var product:Product= Product(added.id.toLong(),added.name,calories,added.grams.toString(),added.image,mAuth.currentUser?.email!!)

        CoroutineScope(Dispatchers.IO).launch {
            firstFragmentViewModel.loadwithProducts( mAuth.currentUser?.email!!);

            firstFragmentViewModel.updateMealUser(
                (firstFragmentViewModel.getwithProducts().playList.calories.toDouble()+product.calories.toDouble()).toString(),
                (firstFragmentViewModel.getwithProducts().playList.caloriesRem.toDouble()-product.calories.toDouble()).toString()
                , mAuth.currentUser?.email!!
            )

          withContext(Dispatchers.Main)
            {
                firstFragmentViewModel.dodadiProduct(product)
                Thread.sleep(1000);

                findNavController().navigate(R.id.action_addProductFragment2_to_caloriesDaily);
            }

        }

    }

    fun onItemClick2(position: String) {
        var added=searchedProducts[position.toInt()]
        var calories=""+added.calories
         product= Product(added.id.toLong(),added.name,calories,added.grams.toString(),added.image,mAuth.currentUser?.email!!)

        val showpopUp= FoodDialogFragment(this);
        showpopUp.show((activity as AppCompatActivity).supportFragmentManager,"showpopUp")


    }


}