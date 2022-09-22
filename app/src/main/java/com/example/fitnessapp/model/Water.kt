package com.example.fitnessapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Water (

@PrimaryKey
    var username:String,
    var waterRem:Double,
    var waterConsumed:Double,
 var cups:String

)