package com.example.fitnessapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UserMeal (
    @PrimaryKey
    var username:String="",

    var status:String="",

)