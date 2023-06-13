package com.example.movieapp.presentation.movies.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.movieapp.Screen
import com.example.movieapp.presentation.components.ShimmerEffect
import com.example.movieapp.presentation.movies.MoviesEvent
import com.example.movieapp.presentation.movies.MoviesViewModel
import com.example.movieapp.ui.theme.MEDIUM_PADDING
import com.example.movieapp.ui.theme.SMALL_PADDING
import com.example.movieapp.ui.theme.statusBarColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieScreen(
    navController: NavController,
    viewModel : MoviesViewModel = hiltViewModel()
) {

    val systemUiController = rememberSystemUiController()
    val systemBarColor = MaterialTheme.colors.statusBarColor
    val state = viewModel.state.value

    var isLoading by remember{
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = true){
        delay(2000)
        isLoading = false
    }

    SideEffect {
        systemUiController.setStatusBarColor(
            color = systemBarColor
        )
    }



    Scaffold(
        topBar = {
            HomeTopBar(
            )
        },
        content = {
    Column {
        MovieSearchBar(modifier = Modifier
            .fillMaxWidth()
            .padding(MEDIUM_PADDING),
            hint = "Joker",
            onSearch = {
                viewModel.onEvent(MoviesEvent.Search(it))
            }
        )
    LazyColumn(
        contentPadding = PaddingValues(all = SMALL_PADDING),
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
    ){
        items(state.movies) { movie ->
            ShimmerEffect(isLoading = isLoading,
                contentAfterLoading = {
                    MovieListRow(movie = movie, onItemClick = {
                        navController.navigate(Screen.MovieDetailScreen.route+"/${movie.imdbID}")
                    })
                }
            )
        }
    }
    Box {
        if (state.error.isNotBlank()) {
            Text(text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MEDIUM_PADDING)
                    .align(Alignment.Center)
            )
        }


        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
    }
        })

}



