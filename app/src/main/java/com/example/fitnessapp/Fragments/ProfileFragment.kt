package com.example.fitnessapp.Fragments

import android.content.Intent
import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.example.fitnessapp.LoginActivity
import com.example.fitnessapp.MainActivity
import com.example.fitnessapp.PersonalizationActivity
import com.example.fitnessapp.R
import com.example.fitnessapp.database.dao.FirstFragmentMealModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text


class ProfileFragment : Fragment() {

    private lateinit var firstFragmentViewModel: FirstFragmentMealModel
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firstFragmentViewModel= ViewModelProvider(this).get(FirstFragmentMealModel::class.java)



        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val image: ImageView =view.findViewById(R.id.profileImage)
        Glide.with(this).load("https://t3.ftcdn.net/jpg/03/46/83/96/360_F_346839683_6nAPzbhpSkIpb8pmAwufkC7c5eD7wYws.jpg").into(image);
        CoroutineScope(Dispatchers.IO).launch {
            firstFragmentViewModel.loadwithProducts(mAuth.currentUser?.email!!)

            withContext(Dispatchers.Main)
            {
               view.findViewById<TextView>(R.id.profileName).text=
                   firstFragmentViewModel.getwithProducts().playList.name.toString()
                view.findViewById<TextView>(R.id.ageProfile).text=
                    firstFragmentViewModel.getwithProducts().playList.godini.toString()+"y"
                view.findViewById<TextView>(R.id.heightProfile).text=
                    firstFragmentViewModel.getwithProducts().playList.visina.toString()+"cm"
                view.findViewById<TextView>(R.id.weightProfile).text=
                    firstFragmentViewModel.getwithProducts().playList.tezina.toString()+"kg"

                view.findViewById<TextView>(R.id.emailProfile).text=mAuth.currentUser?.email!!



                println("https://t3.ftcdn.net/jpg/03/46/83/96/360_F_346839683_6nAPzbhpSkIpb8pmAwufkC7c5eD7wYws.jpg")
            }

        }

            view.findViewById<AppCompatButton>(R.id.button777).setOnClickListener(){
                CoroutineScope(Dispatchers.IO).launch {

                    firstFragmentViewModel.delete(mAuth.currentUser?.email!!)

                    val intent: Intent = Intent(context, PersonalizationActivity::class.java)
                    startActivity(intent)

                }
            }


    }

}





