package com.example.movielist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private val context: Context, private val movies: List<MovieDetails>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val moviePoster: ImageView = itemView.findViewById(R.id.iv_movie_poster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        val poster = movie.poster_path
        val imgUrl = "https://image.tmdb.org/t/p/original$poster"
        Glide.with(context).load(imgUrl).into(holder.moviePoster)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, SingleMovieDetailsActivity::class.java)
            intent.putExtra("movie_poster", imgUrl)
            intent.putExtra("movie_title", movie.title)
            intent.putExtra("movie_release_date", movie.release_date)
            intent.putExtra("movie_rating", movie.vote_average)
            intent.putExtra("movie_overview", movie.overview)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

}