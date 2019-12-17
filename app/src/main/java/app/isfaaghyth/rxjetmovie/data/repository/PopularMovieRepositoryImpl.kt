package app.isfaaghyth.rxjetmovie.data.repository

import app.isfaaghyth.rxjetmovie.data.entity.Movies
import app.isfaaghyth.rxjetmovie.network.Services
import io.reactivex.Flowable
import javax.inject.Inject

open class PopularMovieRepositoryImpl @Inject constructor(
    private val services: Services
) : PopularMovieRepository {

    override fun getPopularMovie(): Flowable<Movies> {
        return services.getPopularMovie()
    }

}