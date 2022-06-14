package com.example.movielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_more_details.*

class MoreDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_details)
        window.statusBarColor = ContextCompat.getColor(this, R.color.grey_700)
        iv_back_arrow_more_details.setOnClickListener { finish() }

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.adapterM1 = MovieAdapterMoreDetails(this, viewModel.resultsTrendingMore)
        recycler_view_more_details.adapter = viewModel.adapterM1
        val layoutManager = GridLayoutManager(this, 2)
        recycler_view_more_details.layoutManager = layoutManager
        viewModel.getTrendingMoviesMore()

//      RecyclerView Pagination
        recycler_view_more_details.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total = viewModel.adapterM1.itemCount

                if ((visibleItemCount + pastVisibleItem) >= total) {
                    viewModel.pageNum++
                    viewModel.getTrendingMoviesMore()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })

    }


}