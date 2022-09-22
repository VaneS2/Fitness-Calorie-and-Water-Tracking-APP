package com.example.fitnessapp.database.dao

import androidx.room.*
import com.example.fitnessapp.database.RelationForProducts
import com.example.fitnessapp.model.MealUser
import com.example.fitnessapp.model.Product
import com.example.fitnessapp.model.Water

@Dao
abstract  class MealUserDao() {



    @Transaction
    @Query("SELECT * FROM MealUser WHERE username = :username")
    abstract fun getCalloriewithProducts(username: String) : RelationForProducts


    @Query("DELETE FROM MealUser WHERE username = :userId")
    abstract fun deleteByUserId(userId: String)


    @Query("UPDATE MealUser SET calories = :calories , caloriesRem = :caloriesRem WHERE username = :username")
    abstract fun updateMealUser(calories: String, caloriesRem: String,username: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMeal(mealUser: MealUser)

    @Transaction
    @Query("SELECT * FROM MealUser WHERE username= :username")
    abstract fun get(username:String):MutableList<MealUser>




    @Query("UPDATE Water SET cups  = :cups , waterRem = :waterRem,waterConsumed= :waterConsumed  WHERE username = :username")
    abstract fun updateWater(cups: String, waterRem: Double,waterConsumed:Double,username: String)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertWater(water: Water)

    @Transaction
    @Query("SELECT * FROM Water WHERE username= :username")
    abstract fun getWater(username:String):Water





}