package com.example.movieapp.data.di

import android.content.Context
import com.example.movieapp.data.remote.MovieAPI
import com.example.movieapp.data.repository.DataOperationsImpl
import com.example.movieapp.data.repository.MovieRepositoryImpl
import com.example.movieapp.data.repository.Repository
import com.example.movieapp.domain.repository.DataStoreOperations
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.domain.use_case.UseCases
import com.example.movieapp.domain.use_case.get_movie_detail.GetMovieDetailsUseCase
import com.example.movieapp.domain.use_case.get_movies.GetMoviesUseCase
import com.example.movieapp.domain.use_case.read_onboarding.ReadOnBoardingUseCase
import com.example.movieapp.domain.use_case.save_onboarding.SaveOnBoardingUseCase
import com.example.movieapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMovieApi() : MovieAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOperations {
        return DataOperationsImpl(context = context)
    }

    @Singleton
    @Provides
    fun provideMovieRepository(
         api : MovieAPI
    ) : MovieRepository{
        return MovieRepositoryImpl(api = api)
    }

    @Provides
    @Singleton
    fun provideUseCases(
        repository : Repository,
        movieRepository : MovieRepository
    ): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            getMovieDetailsUseCase = GetMovieDetailsUseCase(movieRepository),
            getMoviesUseCase = GetMoviesUseCase(movieRepository),
        )
    }
}