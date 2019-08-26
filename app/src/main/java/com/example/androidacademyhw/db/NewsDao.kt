package com.example.androidacademyhw.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(newsEntity: NewsEntity)

    @Query("SELECT * FROM news")
    fun getAllNews(): Observable<List<NewsEntity>>

    @Query("SELECT * FROM news WHERE _id = :id")
    fun getNewsById(id: Int): NewsEntity
}