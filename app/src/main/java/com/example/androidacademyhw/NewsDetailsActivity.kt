package com.example.androidacademyhw

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.androidacademyhw.data.NewsItem

class NewsDetailsActivity : AppCompatActivity() {
    private lateinit var ivDetailPic: ImageView
    private lateinit var tvDetailHeader: TextView
    private lateinit var tvDetailText: TextView
    private lateinit var tvDetailDate:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.placeholder)
            .fallback(R.drawable.placeholder)
            .centerCrop()
        val imageLoader = Glide.with(this).applyDefaultRequestOptions(requestOptions)

        ivDetailPic = findViewById(R.id.iv_detail_pic)
        tvDetailHeader = findViewById(R.id.tv_detail_header)
        tvDetailDate = findViewById(R.id.tv_detail_date)
        tvDetailText = findViewById(R.id.tv_detail_text)

        val news = intent?.extras?.get(NEWS_ITEM_KEY) as NewsItem
        imageLoader.load(news.imageUrl).into(ivDetailPic)
        tvDetailHeader.text = news.title
        tvDetailDate.text = news.publishDate.toString()
        tvDetailText.text = news.fullText

        val toolbar = findViewById<Toolbar>(R.id.detail_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = news.category.name
    }

    companion object {
        val NEWS_ITEM_KEY = "news_key"

        fun startActivity(activity: Activity, newsItem: NewsItem) {
            val intent = Intent(activity, NewsDetailsActivity::class.java)
            intent.putExtra(NEWS_ITEM_KEY, newsItem)
            activity.startActivity(intent)
        }
    }
}
