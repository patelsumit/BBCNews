package com.app.bbcnewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.app.bbcnewsapp.adapters.NewsAdapter
import com.app.bbcnewsapp.models.NewsList
import com.app.bbcnewsapp.utils.isInternet
import com.app.bbcnewsapp.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recycleView = findViewById<RecyclerView>(R.id.newListView)
        swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipeLayout)
        recycleView.layoutManager = LinearLayoutManager(this)
        newsAdapter = NewsAdapter()
        recycleView.adapter = newsAdapter
        initViewModel()
    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getNewsObserval().observe(this, Observer<NewsList> {
            if (it != null) {
                newsAdapter.setData(it.headlines)
            } else {
                Toast.makeText(this, "News data not found...", Toast.LENGTH_SHORT).show()
            }
        })
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            callService(viewModel)
        }
        callService(viewModel)
    }

    private fun callService(viewModel: MainActivityViewModel) {
        if (isInternet(this)) {
            viewModel.webApiCall()
        } else {
            Toast.makeText(this, "There is no internet connection...", Toast.LENGTH_SHORT)
                .show()
        }
    }
}