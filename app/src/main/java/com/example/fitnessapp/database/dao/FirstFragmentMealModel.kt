package com.example.fitnessapp.database.dao

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.fitnessapp.database.AppDatabase
import com.example.fitnessapp.database.RelationForProducts
import com.example.fitnessapp.model.MealUser
import com.example.fitnessapp.model.Product
import com.example.fitnessapp.model.Water

import kotlinx.coroutines.*

class FirstFragmentMealModel(application: Application):AndroidViewModel(application) {
    private var app:Application = application
    private val database: AppDatabase = AppDatabase.getInstance(application)
    private var lista:MutableList<MealUser> = mutableListOf<MealUser>()
    private var izbranfilm:MealUser
    private lateinit var water:Water

    private lateinit var koj:RelationForProducts


    init {
        izbranfilm= MealUser()

    }


fun delete(username: String)
{

    database.MealDao().deleteByUserId(username)
}
    fun save(mealUser:MealUser)
    {
        CoroutineScope(Dispatchers.IO).launch {
            database.MealDao().insertMeal(mealUser)

        }


    }

    fun dodadiProduct(product:Product)
    {
        CoroutineScope(Dispatchers.IO).launch {
            database.ProductDao().insertProduct(product)

        }


    }

    fun loadwithProducts(username:String)
    {

            koj = database.MealDao().getCalloriewithProducts(username)

    }


    fun getwithProducts():RelationForProducts
    {
        return koj;

    }

    fun updateMealUser(calories: String, caloriesRem: String,username: String)
    {
        database.MealDao().updateMealUser(calories,caloriesRem,username)

    }
    fun brisiProduct(username: String)
    {
        database.ProductDao().deleteByUserId(username);
    }




     fun loadIzbran(id:String,)
    {

            lista = database.MealDao().get(id)



    }

    fun getIzbran():
            MutableList<MealUser>
    {

        return lista


    }


    fun updateWater (cups: String, waterRem: Double,waterConsumed:Double,username: String)
    {
        database.MealDao().updateWater(cups,waterRem,waterConsumed,username)
    }

    fun insertWater(water:Water)
    {
        database.MealDao().insertWater(water)
    }

     fun loadWater(username:String)
     {
        water= database.MealDao().getWater(username)
     }

    fun getWater():Water
    {
        return water;
    }





}