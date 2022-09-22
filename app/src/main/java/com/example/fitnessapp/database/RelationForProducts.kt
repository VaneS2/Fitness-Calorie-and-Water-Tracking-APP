package com.example.fitnessapp.database

import androidx.room.Embedded
import androidx.room.Relation
import com.example.fitnessapp.model.MealUser
import com.example.fitnessapp.model.Product


data class RelationForProducts (
    @Embedded
    val playList: MealUser,
    @Relation(
        parentColumn = "username",
        entityColumn = "zakogo"
    )
    val products: MutableList<Product>
)