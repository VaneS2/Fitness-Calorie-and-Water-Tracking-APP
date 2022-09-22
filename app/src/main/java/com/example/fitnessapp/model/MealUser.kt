package com.example.fitnessapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MealUser (
    @PrimaryKey
    var username:String="",

    var name:String="",

    var godini:String="",

    var visina:String="",

    var tezina:String="",

    var status:String="",

    var calories:String="",

    var caloriesRem:String="",




)