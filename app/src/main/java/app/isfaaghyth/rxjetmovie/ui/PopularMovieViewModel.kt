package app.isfaaghyth.rxjetmovie.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.isfaaghyth.rxjetmovie.abstraction.base.BaseViewModel
import app.isfaaghyth.rxjetmovie.abstraction.state.LoaderState
import app.isfaaghyth.rxjetmovie.abstraction.util.rx.SchedulerProvider
import app.isfaaghyth.rxjetmovie.data.domain.PopularMovieUseCase
import app.isfaaghyth.rxjetmovie.data.entity.Movies
import javax.inject.Inject

interface PopularMovieContract {
    fun onErrorPopularMovie(throwable: Throwable)
    fun getPopularMovie()
}

class PopularMovieViewModel @Inject constructor(
    private val useCase: PopularMovieUseCase,
    private val schedulerProvider: SchedulerProvider
): BaseViewModel(), PopularMovieContract {

    private val _movies = MutableLiveData<Movies>()
    val movies: LiveData<Movies>
        get() = _movies

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState>
        get() = _state

    override fun getPopularMovie() {
        subscribe(useCase.execute()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnSubscribe { showLoading() }
            .doOnError { onErrorPopularMovie(it) }
            .subscribe {
                hideLoading()
                _movies.postValue(it)
            })
    }

    override fun onErrorPopularMovie(throwable: Throwable) {
        _error.postValue(throwable.message)
        hideLoading()
    }

    private fun showLoading() {
        _state.postValue(LoaderState.ShowLoading)
    }

    private fun hideLoading() {
        _state.postValue(LoaderState.HideLoading)
    }

}