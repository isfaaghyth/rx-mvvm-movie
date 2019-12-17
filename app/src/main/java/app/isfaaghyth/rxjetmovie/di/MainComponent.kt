package app.isfaaghyth.rxjetmovie.di

import app.isfaaghyth.rxjetmovie.di.module.PopularMovieModule
import app.isfaaghyth.rxjetmovie.di.module.PopularMovieViewModelModule
import app.isfaaghyth.rxjetmovie.ui.MainActivity
import dagger.Component

@MovieScope
@Component(modules = [
    PopularMovieModule::class,
    PopularMovieViewModelModule::class
])
interface MainComponent {
    fun inject(activity: MainActivity)
}