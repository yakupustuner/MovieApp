package com.example.movieapp

sealed class Screen (val route:String) {
    object WelcomeScreen : Screen("welcome_screen")
    object MovieScreen : Screen("movie_screen")
    object MovieDetailScreen : Screen("movie_detail_screen")
}
