package com.example.fitnessapp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FitnessNewsApi {



    @GET("/api/1/news?apikey=pub_86653f718920603d0e4fcf467028f32f52a1&q=fitness")
    fun getPlayListById(): Call<ForNewss>


    companion object{
        const val BASE_URL="https://newsdata.io"
    }

}