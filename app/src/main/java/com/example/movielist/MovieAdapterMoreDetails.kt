package com.example.movielist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapterMoreDetails(
    private val context: Context,
    private val movies: List<MovieDetails>
) : RecyclerView.Adapter<MovieAdapterMoreDetails.MovieViewHolderMoreDetails>() {
    class MovieViewHolderMoreDetails(itemViewMore: View) : RecyclerView.ViewHolder(itemViewMore) {
        val moviePosterMore: ImageView =
            itemViewMore.findViewById(R.id.iv_movie_poster_more_details)
        val movieTitleMore: TextView = itemViewMore.findViewById(R.id.tv_movie_title_more_details)
        val movieReleaseDate: TextView =
            itemViewMore.findViewById(R.id.tv_movie_release_date_more_details)
        val movieRating: TextView = itemViewMore.findViewById(R.id.tv_movie_rating_more_details)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolderMoreDetails {
        val viewMore = LayoutInflater.from(parent.context)
            .inflate(R.layout.more_movie_list, parent, false)
        return MovieViewHolderMoreDetails(viewMore)
    }

    override fun onBindViewHolder(holder: MovieViewHolderMoreDetails, position: Int) {
        val movieMore = movies[position]
        holder.movieTitleMore.text = movieMore.title
        holder.movieReleaseDate.text = movieMore.release_date
        holder.movieRating.text = movieMore.vote_average
        val posterMore = movieMore.poster_path
        val imgUrlMore = "https://image.tmdb.org/t/p/original$posterMore"
        Glide.with(context).load(imgUrlMore).into(holder.moviePosterMore)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, SingleMovieDetailsActivity::class.java)
            intent.putExtra("movie_poster", imgUrlMore)
            intent.putExtra("movie_title", movieMore.title)
            intent.putExtra("movie_release_date", movieMore.release_date)
            intent.putExtra("movie_rating", movieMore.vote_average)
            intent.putExtra("movie_overview", movieMore.overview)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}