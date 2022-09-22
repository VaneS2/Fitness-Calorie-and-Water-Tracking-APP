package com.example.fitnessapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product (
@PrimaryKey(autoGenerate = true)
        var id:Long,


    var name:String="",

    var calories:String="",

    var grams:String="",

    var image:String="",

    var zakogo:String


)