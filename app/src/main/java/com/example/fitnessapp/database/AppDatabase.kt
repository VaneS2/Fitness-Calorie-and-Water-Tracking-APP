package com.example.fitnessapp.database

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fitnessapp.database.dao.MealUserDao
import com.example.fitnessapp.database.dao.ProductDao
import com.example.fitnessapp.model.MealUser
import com.example.fitnessapp.model.Product
import com.example.fitnessapp.model.Water

@Database(entities = [MealUser::class,Product::class,Water::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

        abstract fun MealDao(): MealUserDao
    abstract fun ProductDao(): ProductDao

    companion object {

        private var instance: AppDatabase?=null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = createInstance(context)
            }
            return instance!!
        }

        private fun createInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, "mobilniP.db"
            ).build()
        }

    }
}