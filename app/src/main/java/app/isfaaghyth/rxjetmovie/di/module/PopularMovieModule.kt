package app.isfaaghyth.rxjetmovie.di.module

import app.isfaaghyth.rxjetmovie.abstraction.util.rx.AppSchedulerProvider
import app.isfaaghyth.rxjetmovie.abstraction.util.rx.SchedulerProvider
import app.isfaaghyth.rxjetmovie.data.domain.PopularMovieUseCase
import app.isfaaghyth.rxjetmovie.data.repository.PopularMovieRepository
import app.isfaaghyth.rxjetmovie.data.repository.PopularMovieRepositoryImpl
import app.isfaaghyth.rxjetmovie.di.MovieScope
import app.isfaaghyth.rxjetmovie.network.Network
import app.isfaaghyth.rxjetmovie.network.Services
import dagger.Module
import dagger.Provides
import retrofit2.create

@Module class PopularMovieModule {

    @Provides
    @MovieScope
    fun provideNetworkBuilder(): Services {
        return Network.builder().create()
    }

    @Provides
    @MovieScope
    fun providePopularMovieRepository(services: Services): PopularMovieRepository {
        return PopularMovieRepositoryImpl(services)
    }

    @Provides
    @MovieScope
    fun providePopularMovieUseCase(repository: PopularMovieRepository): PopularMovieUseCase {
        return PopularMovieUseCase(repository)
    }

    @Provides
    @MovieScope
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

}