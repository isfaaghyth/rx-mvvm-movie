package app.isfaaghyth.rxjetmovie.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import app.isfaaghyth.rxjetmovie.R
import app.isfaaghyth.rxjetmovie.abstraction.state.LoaderState
import app.isfaaghyth.rxjetmovie.abstraction.util.showToast
import app.isfaaghyth.rxjetmovie.abstraction.util.viewModelProvider
import app.isfaaghyth.rxjetmovie.data.entity.Movies
import app.isfaaghyth.rxjetmovie.di.DaggerMainComponent
import app.isfaaghyth.rxjetmovie.di.module.PopularMovieModule
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: PopularMovieViewModel

    private val movies = mutableListOf<Movies.Movie>()
    private lateinit var _adapter: PopularMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initInjector()

        viewModel = viewModelProvider(viewModelFactory)
        viewModel.getPopularMovie()

        initView()
        initObservable()
    }

    private fun initView() {
        _adapter = PopularMovieAdapter(movies)
        lstMovies.apply {
            layoutManager = GridLayoutManager(
                this@MainActivity,
                2)
            adapter = _adapter
        }
    }

    private fun initObservable() {
        viewModel.error.observe(this, Observer {
            showToast(it)
        })

        viewModel.state.observe(this, Observer {
            when(it) {
                is LoaderState.ShowLoading -> {
                    showToast("Loading...")
                }
                is LoaderState.HideLoading -> {
                    showToast("Complete!")
                }
            }
        })

        viewModel.movies.observe(this, Observer {
            movies.clear()
            movies.addAll(it.results)
            _adapter.notifyDataSetChanged()
        })
    }

    private fun initInjector() {
        DaggerMainComponent
            .builder()
            .popularMovieModule(PopularMovieModule())
            .build()
            .inject(this)
    }

}
