package com.example.movielist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainMovieLoad()
        imageSlider()
        refreshLayout.setOnRefreshListener {
            mainMovieLoad()
            imageSlider()
            // If "true" it implicitly refreshes forever
            refreshLayout.isRefreshing = false
        }


//       More movies Activity
        cl_trending_movies.setOnClickListener {
            val intent = Intent(this, MoreDetailsActivity::class.java)
            startActivity(intent)
        }

        cl_popular_movies.setOnClickListener {
            val intent = Intent(this, MorePopularActivity::class.java)
            startActivity(intent)
        }

        cl_now_playing_movies.setOnClickListener {
            val intent = Intent(this, NowPlayingActivity::class.java)
            startActivity(intent)
        }

        cl_top_rated_movies.setOnClickListener {
            val intent = Intent(this, MoreTopRatedActivity::class.java)
            startActivity(intent)
        }

    }

    private fun mainMovieLoad() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.adapter = MovieAdapter(this, viewModel.resultsTrending)
        recycler_view_trending_movies.adapter = viewModel.adapter
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler_view_trending_movies.layoutManager = layoutManager
        viewModel.getTrendingMovies()

        viewModel.adapter2 = MovieAdapter(this, viewModel.resultsPopular)
        recycler_view_popular_movies.adapter = viewModel.adapter2
        val layoutManager2 = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler_view_popular_movies.layoutManager = layoutManager2
        viewModel.getPopularMovies()

        viewModel.adapter3 = MovieAdapter(this, viewModel.resultsNowPlaying)
        recycler_view_now_playing_movies.adapter = viewModel.adapter3
        val layoutManager3 = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler_view_now_playing_movies.layoutManager = layoutManager3
        viewModel.getNowPlayingMovies()

        viewModel.adapter4 = MovieAdapter(this, viewModel.resultsTopRated)
        recycler_view_top_rated_movies.adapter = viewModel.adapter4
        val layoutManager4 = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler_view_top_rated_movies.layoutManager = layoutManager4
        viewModel.getTopRatedMovies()
    }

    private fun imageSlider() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        val imageList: ArrayList<String> = ArrayList()
        imageList.add("https://image.tmdb.org/t/p/original/cinER0ESG0eJ49kXlExM0MEWGxW.jpg")
        imageList.add("https://image.tmdb.org/t/p/original/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg")
        imageList.add("https://image.tmdb.org/t/p/original/fzKWwcaam9QSTaMSJlORuSojxio.jpg")
        viewModel.setImageInSlider(imageList, imageSlider,this)
    }
}


// 15/10/2021