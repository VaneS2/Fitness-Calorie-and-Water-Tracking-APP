package com.example.fitnessapp.Fragments.News

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.adapter.BasicRecyclerViewAdapter

import com.example.fitnessapp.model.FitnessNews
import com.example.fitnessapp.model.FitnessNewsApi
import com.example.fitnessapp.model.ForNewss
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NewsFragment : Fragment() {
    private lateinit var user: FirebaseAuth




    lateinit var allNews: MutableList<FitnessNews>
    lateinit var recyclerViewAdapter: BasicRecyclerViewAdapter

    lateinit var recyclerkompomenta: RecyclerView



    private lateinit var c: Context





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_first, container, false)

     /*   val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(
            R.id.fragmentContainer
        ) as NavHostFragment
        navController = navHostFragment.navController
*/


        return view;

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //binding.buttonFirst.setOnClickListener {
          //  findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
       // }
        var ap= Retrofit.Builder()
            .baseUrl(FitnessNewsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var API=ap.create(FitnessNewsApi::class.java)

        var call=API.getPlayListById()

        call?.enqueue(object: retrofit2.Callback<ForNewss> {
            override fun onResponse(call: Call<ForNewss>?, response: Response<ForNewss>?) {
                val novosti:ForNewss = response?.body() as ForNewss

                if (novosti != null) {

                    allNews=novosti.results;
                    recyclerViewAdapter.updateData(novosti.results)
                }
            }

            override fun onFailure(call: Call<ForNewss>?, t: Throwable?) {
                TODO("Not yet implemented")
            }


        })

        allNews= mutableListOf();
        var news:FitnessNews= FitnessNews("New1","https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg","hehe","hehehehe","hehhhha")

        recyclerkompomenta = view.findViewById(R.id.RecyclerNews)

        recyclerkompomenta.layoutManager = LinearLayoutManager(context)

        recyclerViewAdapter= BasicRecyclerViewAdapter(
            this, allNews,
            this)

        recyclerkompomenta.adapter=recyclerViewAdapter;

        }

    override fun onDestroyView() {
        super.onDestroyView()

    }



     fun onItemClick(id: String) {
        val bundle:Bundle= Bundle()

        var koj:FitnessNews=allNews[id.toInt()]

        bundle.putString("title",koj.title)
         bundle.putString("content",koj.content)
         bundle.putString("description",koj.description)
         bundle.putString("image_url",koj.image_url)
         bundle.putString("link",koj.link)

         findNavController().navigate(R.id.action_FirstFragment_to_newContentView,bundle)


    }

}