package com.example.movieapp.presentation.movies.views

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.example.movieapp.ui.theme.appBarBackgroundColor
import com.example.movieapp.ui.theme.appBarContentColor

@Composable
fun HomeTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Movie App",
                color = MaterialTheme.colors.appBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.appBarBackgroundColor,
        )
}