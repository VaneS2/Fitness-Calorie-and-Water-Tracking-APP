package com.example.fitnessapp.Fragments.Meal

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.lifecycle.ViewModelProvider
import com.example.fitnessapp.MainActivity

import com.example.fitnessapp.database.dao.FirstFragmentMealModel
import com.example.fitnessapp.model.MealUser
import com.google.firebase.auth.FirebaseAuth
import okhttp3.*

import java.io.IOException
import com.google.gson.JsonParser

import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.R
import android.widget.EditText
import com.example.fitnessapp.LoginActivity
import com.example.fitnessapp.model.Product
import com.example.fitnessapp.model.Water
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.fragment_info.*
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt


class InfoFragment : Fragment() {
    var promenliva:String="";

    private lateinit var firstFragmentViewModel: FirstFragmentMealModel

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firstFragmentViewModel= ViewModelProvider(this).get(FirstFragmentMealModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {





        return inflater.inflate(com.example.fitnessapp.R.layout.fragment_info, container, false)







    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var gender="";

        forGender.setOnCheckedChangeListener{forGender, i->
            var rb:RadioButton = view.findViewById<RadioButton>(i)

            if(rb!=null)
            {
                gender=rb.text.toString().lowercase();
                println(gender);
            }
        }

      /*  view.findViewById<Button>(com.example.fitnessapp.R.id.pocini).setOnClickListener()
        {
            var user: FirebaseAuth =FirebaseAuth.getInstance()
            user.signOut()
        }*/

        view.findViewById<Button>(com.example.fitnessapp.R.id.AddToDataBase).setOnClickListener()
        {

            var goal=arguments?.getString("goal");
            var level=arguments?.getString("level");


            val visina: EditText = view.findViewById(com.example.fitnessapp.R.id.visina)
            val tezina: EditText = view.findViewById(com.example.fitnessapp.R.id.tezina)
            val godini: EditText = view.findViewById(com.example.fitnessapp.R.id.godini)
            val ime: EditText = view.findViewById(com.example.fitnessapp.R.id.imeKorisnik)

            val client = OkHttpClient()


            val request: Request = Request.Builder()
                .url("https://fitness-calculator.p.rapidapi.com/dailycalorie?age="+godini.text+"&gender="+gender+"&height="+visina.text+"&weight="+tezina.text+"&activitylevel="+level)
                .get()
                .addHeader("X-RapidAPI-Key", "e61705d08fmsh74eb7c4fc18ff4ep1b0d57jsn2b474cffe22a")
                .addHeader("X-RapidAPI-Host", "fitness-calculator.p.rapidapi.com")
                .build()

            CoroutineScope(Dispatchers.IO).launch {

            client.newCall(request).enqueue(object:Callback
            {
                override fun onFailure(call: Call, e: IOException) {

                    println("Failed")
                }

                override fun onResponse(call: Call, response: Response) {



                    var myResponse = response.body?.string()

                    val obj: JsonObject = JsonParser().parse(myResponse).getAsJsonObject()
                    var i=obj.getAsJsonObject("data");
                    var j=i.getAsJsonObject("goals");
                    var calories="";

                    if(goal=="maintain")
                    {
                        calories= j.get("maintain weight").toString()
                    }
                    else if(goal=="lose")
                    {
                        calories=j.getAsJsonObject("Weight loss").getAsJsonPrimitive("calory").toString()
                    }
                    else
                    {
                        calories=j.getAsJsonObject("Weight gain").getAsJsonPrimitive("calory").toString()
                    }


                    var username= mAuth.currentUser?.email?.let { it1 -> MealUser(it1,"200") };




                    if(mAuth.currentUser?.email!=null && goal!=null) {
                        var mealUser: MealUser = MealUser(
                            mAuth.currentUser?.email!! ,
                            ime.text.toString(),
                            godini.text.toString(),visina.text.toString(),tezina.text.toString(),
                            goal,
                            "0",
                            calories
                        )
                        var tez=((tezina.text.toString().toInt()*35)/28.3)*0.0295735296;



                        var water:Water = Water(mAuth.currentUser?.email!!,(tez * 10000.0).roundToInt() / 10000.0,0.00, "")

                        firstFragmentViewModel.insertWater(water);

                        firstFragmentViewModel.save(mealUser)





                    }


                }

            })




        }

            Thread.sleep(4000)

            val intent: Intent = Intent(context, MainActivity::class.java)
            startActivity(intent);

        }
    }

}