package com.example.androidacademyhw.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidacademyhw.data.Category
import java.util.*

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey @ColumnInfo(name = "_id") val _id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "category") val category: Category,
    @ColumnInfo(name = "publish_date") val publishDate: Date,
    @ColumnInfo(name = "preview_text") val previewText: String,
    @ColumnInfo(name = "full_text") val fullText: String)