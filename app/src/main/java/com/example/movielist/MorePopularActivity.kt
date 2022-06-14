package com.example.movielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_more_popular.*

class MorePopularActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_popular)
        window.statusBarColor = ContextCompat.getColor(this, R.color.grey_700)
        iv_back_arrow_more_popular.setOnClickListener { finish() }

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.adapterM2 = MovieAdapterMoreDetails(this, viewModel.resultsPopularMore)
        recycler_view_more_popular.adapter = viewModel.adapterM2
        val layoutManager = GridLayoutManager(this, 2)
        recycler_view_more_popular.layoutManager = layoutManager
        viewModel.getPopularMoviesMore()

        recycler_view_more_popular.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total = viewModel.adapterM2.itemCount

                if ((visibleItemCount + pastVisibleItem) >= total) {
                    viewModel.pageNum++
                    viewModel.getPopularMoviesMore()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }
}