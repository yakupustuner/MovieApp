package com.example.movieapp.domain.use_case

import com.example.movieapp.domain.use_case.get_movie_detail.GetMovieDetailsUseCase
import com.example.movieapp.domain.use_case.get_movies.GetMoviesUseCase
import com.example.movieapp.domain.use_case.read_onboarding.ReadOnBoardingUseCase
import com.example.movieapp.domain.use_case.save_onboarding.SaveOnBoardingUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    val getMoviesUseCase: GetMoviesUseCase,

)
