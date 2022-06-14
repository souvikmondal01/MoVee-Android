package com.example.movielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_more_top_rated.*

class MoreTopRatedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_top_rated)
        window.statusBarColor = ContextCompat.getColor(this, R.color.grey_700)
        iv_back_arrow_more_top_rated.setOnClickListener { finish() }

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.adapterM4 = MovieAdapterMoreDetails(this, viewModel.resultsTopRatedMore)
        recycler_view_more_top_rated.adapter = viewModel.adapterM4
        val layoutManager = GridLayoutManager(this, 2)
        recycler_view_more_top_rated.layoutManager = layoutManager
        viewModel.getTopRatedMoviesMore()

        recycler_view_more_top_rated.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total = viewModel.adapterM4.itemCount

                if ((visibleItemCount + pastVisibleItem) >= total) {
                    viewModel.pageNum++
                    viewModel.getTopRatedMoviesMore()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })

    }
}