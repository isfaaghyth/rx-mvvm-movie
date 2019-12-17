package app.isfaaghyth.rxjetmovie.network

import app.isfaaghyth.rxjetmovie.data.entity.Movies
import io.reactivex.Flowable
import retrofit2.http.GET

interface Services {

    @GET(POPULAR_MOVIE)
    fun getPopularMovie(): Flowable<Movies>

    companion object {
        private const val POPULAR_MOVIE = "movie/popular"
    }

}