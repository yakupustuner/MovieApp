package com.example.movieapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.example.movieapp.Screen
import com.example.movieapp.domain.use_case.UseCases
import com.example.movieapp.presentation.movie_detail.views.MovieDetailScreen
import com.example.movieapp.presentation.movies.views.MovieScreen
import com.example.movieapp.presentation.welcome.views.WelcomeScreen
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.util.Constants.IMDB_ID
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    @Inject
    lateinit var useCases: UseCases

    private var completed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            MovieAppTheme {
                navController = rememberNavController()
                  NavHost(
                      navController = navController,
                      startDestination = if (completed) Screen.MovieScreen.route
                      else Screen.WelcomeScreen.route
                  )
                  {
                      composable(route = Screen.WelcomeScreen.route){
                          WelcomeScreen(navController = navController)
                      }
                      composable(route = Screen.MovieScreen.route){
                         MovieScreen(navController = navController)
                      }
                      composable(route = Screen.MovieDetailScreen.route+"/{${IMDB_ID}}"){
                          MovieDetailScreen(navController = navController)
                      }
                  }
            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            useCases.readOnBoardingUseCase.invoke().collect{
                completed = it
            }
        }
    }
}


