package com.example.movielist

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


//https://api.themoviedb.org/3/trending/all/day?api_key=676501002986df75334b86def9839a75
//https://api.themoviedb.org/3/movie/now_playing?api_key=676501002986df75334b86def9839a75&page=1
//https://api.themoviedb.org/3/movie/popular?api_key=676501002986df75334b86def9839a75&page=1
//https://api.themoviedb.org/3/movie/top_rated?api_key=676501002986df75334b86def9839a75&page=1

const val BASE_URL = "https://api.themoviedb.org/"
const val API_KEY = "676501002986df75334b86def9839a75"

interface MovieInterface {
    @GET("3/trending/all/day?api_key=$API_KEY")
    fun getTrendingMovies(@Query("page") page: Int): Call<Movies>

    @GET("3/movie/popular?api_key=$API_KEY")
    fun getPopularMovies(@Query("page") page: Int): Call<Movies>

    @GET("3/movie/now_playing?api_key=$API_KEY")
    fun getNowPlayingMovies(@Query("page") page: Int): Call<Movies>

    @GET("3/movie/top_rated?api_key=$API_KEY")
    fun getTopRatedMovies(@Query("page") page: Int): Call<Movies>

}

object MovieService {
    val movieInstance: MovieInterface

    init {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        movieInstance = retrofit.create(MovieInterface::class.java)
    }
}