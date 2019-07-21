package com.example.androidacademyhw

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.example.androidacademyhw.data.DataUtils
import com.example.androidacademyhw.data.NewsItem

class NewsListActivity : AppCompatActivity(), NewsAdapter.OnClickListener {
    private lateinit var rv: RecyclerView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        rv = findViewById(R.id.rv)
        rv.layoutManager = when (this.resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT-> GridLayoutManager(this, 1)
            else -> GridLayoutManager(this, 2)
        }
        val adapter = NewsAdapter(this)
        adapter.submitList(DataUtils().generateNews())
        rv.adapter = adapter
        rv.addItemDecoration(NewsItemListDecorator(4 * resources.displayMetrics.density.toInt()))

        toolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(0, 0, 0,"About")

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            0 -> AboutActivity.openActivity(this)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(item: NewsItem) {
        NewsDetailsActivity.startActivity(this, item)
    }
}
