package com.example.androidacademyhw

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.androidacademyhw.data.DataUtils
import com.example.androidacademyhw.data.NewsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NewsListActivity : AppCompatActivity(), NewsAdapter.OnClickListener {
    private lateinit var rv: RecyclerView
    private lateinit var toolbar: Toolbar
    private lateinit var progressBar: ProgressBar

    private var job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        progressBar = findViewById(R.id.status_progress)
        val dataUtils = DataUtils()
        dataUtils.progress.observe(this, Observer {
            if (it == 1)
                progressBar.visibility = View.INVISIBLE
        })

        rv = findViewById(R.id.rv)
        rv.layoutManager = when (this.resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT-> androidx.recyclerview.widget.GridLayoutManager(this, 1)
            else -> androidx.recyclerview.widget.GridLayoutManager(this, 2)
        }
        val adapter = NewsAdapter(this)

        uiScope.launch {
            adapter.submitList(dataUtils.generateNews())
        }

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

    override fun onStop() {
        super.onStop()

        job.cancel()
    }
}
