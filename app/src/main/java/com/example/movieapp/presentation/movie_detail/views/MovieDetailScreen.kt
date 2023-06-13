package com.example.movieapp.presentation.movie_detail.views

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.movieapp.R
import com.example.movieapp.presentation.components.InfoBox
import com.example.movieapp.presentation.movie_detail.MovieDetailViewModel
import com.example.movieapp.ui.theme.Actors
import com.example.movieapp.ui.theme.Calendar
import com.example.movieapp.ui.theme.Director
import com.example.movieapp.ui.theme.EXPANDED_RADIUS_LEVEL
import com.example.movieapp.ui.theme.EXTRA_LARGE_PADDING
import com.example.movieapp.ui.theme.INFO_ICON_SIZE
import com.example.movieapp.ui.theme.LARGE_PADDING
import com.example.movieapp.ui.theme.Location
import com.example.movieapp.ui.theme.MEDIUM_PADDING
import com.example.movieapp.ui.theme.MIN_SHEET_HEIGHT
import com.example.movieapp.ui.theme.Rating
import com.example.movieapp.ui.theme.SMALL_PADDING
import com.example.movieapp.ui.theme.statusBarColor
import com.example.movieapp.ui.theme.titleColor
import com.example.movieapp.util.Constants.MIN_BACKGROUND_IMAGE_HEIGHT
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieDetailScreen(
    navController: NavHostController,
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state = movieDetailViewModel.state.value

    val systemUiController = rememberSystemUiController()
    val systemBarColor = MaterialTheme.colors.statusBarColor
    SideEffect {
        systemUiController.setStatusBarColor(
            color = systemBarColor
        )
    }

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )
    val currentSheetFraction = scaffoldState.currentSheetFraction

    val radiusAnim by animateDpAsState(
        targetValue =
        if (currentSheetFraction == 1f)
            EXTRA_LARGE_PADDING
        else
            EXPANDED_RADIUS_LEVEL
    )
    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = radiusAnim,
            topEnd = radiusAnim
        ),
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            BottomSheetContent(
                sheetBackgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.titleColor,
                title = state.movie?.Title,
                year = state.movie?.Year,
                actors = state.movie?.Actors,
                country = state.movie?.Country,
                director = state.movie?.Director,
                imdbRating = state.movie?.imdbRating

            )
        },
        content = {
            state.movie.let {
                if (it != null) {
                    BackgroundContent(
                        image = it.Poster,
                        imageFraction = currentSheetFraction,
                        backgroundColor = MaterialTheme.colors.surface,
                        onCloseClicked = {
                            navController.popBackStack()
                        }
                    )
                }
            }

        }
    )
    Box {
        if (state.error.isNotBlank()) {
            androidx.compose.material.Text(
                text = state.error,
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



@OptIn(ExperimentalCoilApi::class)
@Composable
fun BackgroundContent(
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.surface,
    onCloseClicked: () -> Unit,
    image: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Image(painter = rememberImagePainter(data = image),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction + MIN_BACKGROUND_IMAGE_HEIGHT)
                .align(Alignment.TopStart),
            contentDescription = stringResource(id = R.string.movie_image),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.padding(all = SMALL_PADDING),
                onClick = { onCloseClicked() }
            ) {
                Icon(
                    modifier = Modifier.size(INFO_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.close_icon),
                    tint = Color.Black
                )
            }
        }
    }

}

@Composable
fun BottomSheetContent(
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.titleColor,
    title: String?,
    year: String?,
    actors: String?,
    country: String?,
    director: String?,
    imdbRating: String?
) {
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(all = LARGE_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (title != null) {
                Text(
                    modifier = Modifier
                        .weight(8f),
                    text = title,
                    color = contentColor,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = SMALL_PADDING),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            InfoBox(
                icon = painterResource(id = R.drawable.baseline_movie_24),
                titleText = "$title",
                textColor = contentColor,
                colorFilter = ColorFilter.tint(Color.Black)
            )
            InfoBox(
                icon = painterResource(id = R.drawable.baseline_calendar_month_24),
                titleText = "$year",
                textColor = contentColor,
                colorFilter = ColorFilter.tint(Calendar)
            )
            InfoBox(
                icon = painterResource(id = R.drawable.baseline_recent_actors_24),
                titleText = "$actors",
                textColor = contentColor,
                colorFilter = ColorFilter.tint(Actors)
            )
            InfoBox(
                icon = painterResource(id = R.drawable.baseline_location_on_24),
                titleText = "$country",
                textColor = contentColor,
                colorFilter = ColorFilter.tint(Location)
            )
            InfoBox(
                icon = painterResource(id = R.drawable.baseline_crop_free_24),
                titleText = "$director",
                textColor = contentColor,
                colorFilter = ColorFilter.tint(Director)

            )
            InfoBox(
                icon = painterResource(id = R.drawable.baseline_star_24),
                titleText = "$imdbRating",
                textColor = contentColor,
                colorFilter = ColorFilter.tint(Rating)

            )
        }



    }

}




@ExperimentalMaterialApi
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val fraction = bottomSheetState.progress.fraction
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed -> 1f
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded -> 0f
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Expanded -> 1f - fraction
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Collapsed -> 0f + fraction
            else -> fraction
        }
    }

