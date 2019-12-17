package app.isfaaghyth.rxjetmovie.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.isfaaghyth.rxjetmovie.R
import app.isfaaghyth.rxjetmovie.abstraction.util.load
import app.isfaaghyth.rxjetmovie.abstraction.util.showToast
import app.isfaaghyth.rxjetmovie.data.entity.Movies
import kotlinx.android.synthetic.main.item_movie.view.*

class PopularMovieAdapter(
    private val movies: List<Movies.Movie>
): RecyclerView.Adapter<PopularMovieAdapter.PopularMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = PopularMovieViewHolder.create(parent)

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    class PopularMovieViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val txtTitle = view.txtMovieTitle
        private val txtYear = view.txtYear
        private val imgPoster = view.imgPoster

        fun bind(movie: Movies.Movie) {
            imgPoster.load(movie.posterUrl())
            txtTitle.text = movie.title
            txtYear.text = movie.releaseDate

            itemView.setOnClickListener {
                onMovieItemClick(movie)
            }
        }

        private fun onMovieItemClick(movie: Movies.Movie) {
            itemView.context.showToast(movie.title)
        }

        companion object {
            fun create(viewGroup: ViewGroup): PopularMovieViewHolder {
                val view = LayoutInflater
                    .from(viewGroup.context)
                    .inflate(R.layout.item_movie, viewGroup, false)
                return PopularMovieViewHolder(view)
            }
        }
    }

}