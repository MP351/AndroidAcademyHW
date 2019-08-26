package com.example.androidacademyhw.db

import android.content.Context
import androidx.room.*

@Database(entities = [NewsEntity::class], version = 1)
abstract class AppDatabase private constructor(): RoomDatabase() {
    abstract val newsDao: NewsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java,
                        "myDB")
                        .allowMainThreadQueries()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }


}