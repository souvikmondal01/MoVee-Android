package com.example.movielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_now_playing_activitty.*

class NowPlayingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_now_playing_activitty)
        window.statusBarColor = ContextCompat.getColor(this, R.color.grey_700)
        iv_back_arrow_more_now_playing.setOnClickListener { finish() }

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.adapterM3 = MovieAdapterMoreDetails(this, viewModel.resultsNowPlayingMore)
        recycler_view_more_now_playing.adapter = viewModel.adapterM3
        val layoutManager = GridLayoutManager(this, 2)
        recycler_view_more_now_playing.layoutManager = layoutManager
        viewModel.getNowPlayingMoviesMore()

        recycler_view_more_now_playing.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total = viewModel.adapterM3.itemCount

                if ((visibleItemCount + pastVisibleItem) >= total) {
                    viewModel.pageNum++
                    viewModel.getNowPlayingMoviesMore()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }
}