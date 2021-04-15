package com.example.recipeapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipeapp.model.category.Category


@Database(
    entities = [Category::class],
    version = 1
)
abstract class CategoryDatabase : RoomDatabase() {

    abstract fun getCategoryDao(): CategoryDao

    companion object
    {
        @Volatile
        private var instance: CategoryDatabase? = null
        private val Lock = Any()

        fun getInstance(context: Context) = instance ?: synchronized(Lock){
            instance ?: createDatabase(context).also { instance = it}
        }

        private fun createDatabase(context: Context)  =
            Room.databaseBuilder(
                context.applicationContext,
                CategoryDatabase::class.java,
                "category_db.db"
            ).build()
    }

}