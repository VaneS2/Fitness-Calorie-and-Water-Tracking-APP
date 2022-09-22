package com.example.fitnessapp.database.dao

import androidx.room.*
import com.example.fitnessapp.database.RelationForProducts
import com.example.fitnessapp.model.MealUser
import com.example.fitnessapp.model.Product

@Dao
abstract  class ProductDao() {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertProduct(product: Product)


    @Query("DELETE FROM Product WHERE zakogo = :username")
    abstract fun deleteByUserId(username: String)


}