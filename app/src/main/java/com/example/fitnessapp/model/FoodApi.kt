package com.example.fitnessapp.model

import android.text.Editable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {



    @GET("/api/food-database/v2/parser?app_id=337219a9&app_key=9c671f719e657c79e8a40b9925528788%09&")
    fun get(@Query("ingr") prebarano: String): Call<Final>


    companion object{
        const val BASE_URL="https://api.edamam.com"
    }

}