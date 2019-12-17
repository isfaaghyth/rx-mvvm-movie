package app.isfaaghyth.rxjetmovie.data.domain

import app.isfaaghyth.rxjetmovie.abstraction.base.UseCase
import app.isfaaghyth.rxjetmovie.data.entity.Movies
import app.isfaaghyth.rxjetmovie.data.repository.PopularMovieRepository
import io.reactivex.Flowable
import javax.inject.Inject

open class PopularMovieUseCase @Inject constructor(
    private val repository: PopularMovieRepository
): UseCase<Flowable<Movies>>() {

    override fun execute(): Flowable<Movies> {
        return repository.getPopularMovie()
    }

}