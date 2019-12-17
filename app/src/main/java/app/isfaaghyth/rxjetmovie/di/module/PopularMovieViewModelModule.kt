package app.isfaaghyth.rxjetmovie.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.isfaaghyth.rxjetmovie.abstraction.util.viewmodel.ViewModelFactory
import app.isfaaghyth.rxjetmovie.abstraction.util.viewmodel.ViewModelKey
import app.isfaaghyth.rxjetmovie.di.MovieScope
import app.isfaaghyth.rxjetmovie.ui.PopularMovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module abstract class PopularMovieViewModelModule {

    @MovieScope
    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PopularMovieViewModel::class)
    internal abstract fun bindMovieViewModel(viewModel: PopularMovieViewModel): ViewModel

}