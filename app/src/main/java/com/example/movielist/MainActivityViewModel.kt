package com.example.movielist

import android.content.Context
import androidx.lifecycle.ViewModel
import com.smarteist.autoimageslider.SliderView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {
    var pageNum = 1

    var resultsTrending = mutableListOf<MovieDetails>()
    var resultsPopular = mutableListOf<MovieDetails>()
    var resultsNowPlaying = mutableListOf<MovieDetails>()
    var resultsTopRated = mutableListOf<MovieDetails>()

    var resultsTrendingMore = mutableListOf<MovieDetails>()
    var resultsPopularMore = mutableListOf<MovieDetails>()
    var resultsNowPlayingMore = mutableListOf<MovieDetails>()
    var resultsTopRatedMore = mutableListOf<MovieDetails>()


    lateinit var adapter: MovieAdapter
    lateinit var adapter2: MovieAdapter
    lateinit var adapter3: MovieAdapter
    lateinit var adapter4: MovieAdapter

    lateinit var adapterM1: MovieAdapterMoreDetails
    lateinit var adapterM2: MovieAdapterMoreDetails
    lateinit var adapterM3: MovieAdapterMoreDetails
    lateinit var adapterM4: MovieAdapterMoreDetails


    fun getTrendingMovies() {
        val movie: Call<Movies> = MovieService.movieInstance.getTrendingMovies(pageNum)
        movie.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                val tm = response.body()
                if (tm != null) {
                    resultsTrending.addAll(tm.results)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
            }
        })
    }

    fun getPopularMovies() {
        val popularMovie: Call<Movies> = MovieService.movieInstance.getPopularMovies(pageNum)
        popularMovie.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                val pm = response.body()
                if (pm != null) {
                    resultsPopular.addAll(pm.results)
                    adapter2.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
            }
        })

    }

    fun getNowPlayingMovies() {
        val nowPlayingMovie: Call<Movies> = MovieService.movieInstance.getNowPlayingMovies(pageNum)
        nowPlayingMovie.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                val npm = response.body()
                if (npm != null) {
                    resultsNowPlaying.addAll(npm.results)
                    adapter3.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<Movies>, t: Throwable) {
            }
        })
    }

    fun getTopRatedMovies() {
        val topRatedMovie: Call<Movies> = MovieService.movieInstance.getTopRatedMovies(pageNum)
        topRatedMovie.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                val trm = response.body()
                if (trm != null) {
                    resultsTopRated.addAll(trm.results)
                    adapter4.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
            }
        })

    }

    fun getTrendingMoviesMore() {
        val movieM: Call<Movies> = MovieService.movieInstance.getTrendingMovies(pageNum)
        movieM.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                val tmm = response.body()
                if (tmm != null) {
                    resultsTrendingMore.addAll(tmm.results)
                    adapterM1.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
            }
        })
    }

    fun getPopularMoviesMore() {
        val popularMovieMore: Call<Movies> = MovieService.movieInstance.getPopularMovies(pageNum)
        popularMovieMore.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                val pmm = response.body()
                if (pmm != null) {
                    resultsPopularMore.addAll(pmm.results)
                    adapterM2.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
            }
        })

    }

    fun getNowPlayingMoviesMore() {
        val nowPlayingMovie: Call<Movies> = MovieService.movieInstance.getNowPlayingMovies(pageNum)
        nowPlayingMovie.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                val npm = response.body()
                if (npm != null) {
                    resultsNowPlayingMore.addAll(npm.results)
                    adapterM3.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
            }
        })
    }

    fun getTopRatedMoviesMore() {
        val topRatedMovies: Call<Movies> = MovieService.movieInstance.getTopRatedMovies(pageNum)
        topRatedMovies.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                val trm = response.body()
                if (trm != null) {
                    resultsTopRatedMore.addAll(trm.results)
                    adapterM4.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
            }
        })
    }

    fun setImageInSlider(images: ArrayList<String>, imageSlider: SliderView,context: Context) {
        val adapter = MySliderImageAdapter(context)
        adapter.renewItems(images)
        imageSlider.setSliderAdapter(adapter)
        imageSlider.isAutoCycle = true
        imageSlider.startAutoCycle()
    }

}