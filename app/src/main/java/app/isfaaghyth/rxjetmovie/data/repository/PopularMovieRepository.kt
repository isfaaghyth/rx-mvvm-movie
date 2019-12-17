package app.isfaaghyth.rxjetmovie.data.repository

import app.isfaaghyth.rxjetmovie.data.entity.Movies
import io.reactivex.Flowable

interface PopularMovieRepository {
    fun getPopularMovie(): Flowable<Movies>
}