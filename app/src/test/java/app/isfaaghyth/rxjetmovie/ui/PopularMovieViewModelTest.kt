package app.isfaaghyth.rxjetmovie.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import app.isfaaghyth.rxjetmovie.abstraction.base.UseCase
import app.isfaaghyth.rxjetmovie.abstraction.state.LoaderState
import app.isfaaghyth.rxjetmovie.abstraction.util.rx.TestSchedulerProvider
import app.isfaaghyth.rxjetmovie.data.domain.PopularMovieUseCase
import app.isfaaghyth.rxjetmovie.data.entity.Movies
import io.reactivex.Flowable
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class PopularMovieViewModelTest {

    /* rule executor */
    @get:Rule val instantExecutorRule = InstantTaskExecutorRule()

    /* prepare for viewmodel (including class dependencies) */
    @Mock lateinit var usecase: PopularMovieUseCase
    private lateinit var viewModel: PopularMovieViewModel

    /* observable and captor */
    @Mock lateinit var moviesObservable: Observer<Movies>
    @Mock lateinit var stateObservable: Observer<LoaderState>

    @Captor lateinit var moviesCaptor: ArgumentCaptor<Movies>
    @Captor lateinit var stateCaptor: ArgumentCaptor<LoaderState>

    /* usecase response mock */
    private val returnValue = Flowable.just(moviesData)

    @Before fun setUp() {
        MockitoAnnotations.initMocks(this)

        //init viewmodel
        val scheduler = TestSchedulerProvider()
        viewModel = PopularMovieViewModel(usecase, scheduler)

        //observe
        viewModel.movies.observeForever(moviesObservable)
        viewModel.state.observeForever(stateObservable)
    }

    @Test fun `it should return response correctly`() {
        `when`(usecase.execute()).thenReturn(returnValue)
        viewModel.getPopularMovie()
        verify(moviesObservable, atLeastOnce()).onChanged(moviesCaptor.capture())
        returnValue
            .test()
            .assertValue {
                it.results.first() == moviesCaptor.value.results.first()
            }
    }

    @Test fun `state handling correctly`() {
        `when`(usecase.execute()).thenReturn(returnValue)
        viewModel.getPopularMovie()
        verify(stateObservable, atLeastOnce()).onChanged(stateCaptor.capture())
        assert(LoaderState.ShowLoading == stateCaptor.allValues[0]) //first, loader is showing
        assert(LoaderState.HideLoading == stateCaptor.allValues[1]) //then, hide the loader
    }

    @Test fun `it should return empty movie`() {
        val returnEmptyValue = Flowable.just(Movies(listOf()))
        `when`(usecase.execute()).thenReturn(returnEmptyValue)
        viewModel.getPopularMovie()
        verify(moviesObservable, atLeastOnce()).onChanged(moviesCaptor.capture())
        assert(moviesCaptor.value.results.isEmpty())
    }

    @After fun tearDown() {
        clearInvocations(usecase, moviesObservable)
    }

    /* mock data */
    companion object {
        private val movies = listOf(
            Movies.Movie(
                "id",
                "movieId",
                "title",
                "posterPath",
                "overview",
                "backdrop",
                0,
                0f,
                "relateDate"
            )
        )

        private val moviesData = Movies(movies)
    }

}
