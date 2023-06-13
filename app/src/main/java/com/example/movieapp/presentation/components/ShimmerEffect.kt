package com.example.movieapp.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.ui.theme.LARGE_PADDING
import com.example.movieapp.ui.theme.MEDIUM_PADDING
import com.example.movieapp.ui.theme.MOVIE_ITEM_HEIGHT
import com.example.movieapp.ui.theme.NAME_PLACEHOLDER_HEIGHT
import com.example.movieapp.ui.theme.SMALL_PADDING
import com.example.movieapp.ui.theme.ShimmerDarkGray
import com.example.movieapp.ui.theme.ShimmerLightGray
import com.example.movieapp.ui.theme.ShimmerMediumGray

@Composable
fun ShimmerEffect(
    isLoading:Boolean,
    contentAfterLoading: @Composable () -> Unit
) {
    if (isLoading){
        ShimmerItem()
    } else {
        contentAfterLoading()
    }

}

@Composable
fun ShimmerItem() {
    val transition = rememberInfiniteTransition()
    val alphaAnim by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ))
    ShimmerItemModifier(alpha = alphaAnim)
}

@Composable
fun ShimmerItemModifier(alpha: Float) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(MOVIE_ITEM_HEIGHT),
        color = if (isSystemInDarkTheme())
            Color.Black else ShimmerLightGray,
        shape = RoundedCornerShape(size = LARGE_PADDING)
    ) {
        Column(
            modifier = Modifier
                .padding(all = MEDIUM_PADDING),
            verticalArrangement = Arrangement.Bottom
        ) {
            Surface(
                modifier = Modifier
                    .alpha(alpha = alpha)
                    .fillMaxWidth(0.5f)
                    .height(NAME_PLACEHOLDER_HEIGHT),
                color = if (isSystemInDarkTheme())
                    ShimmerDarkGray else ShimmerMediumGray,
                shape = RoundedCornerShape(size = SMALL_PADDING)
            ) {}
            repeat(3) {

            }

        }
    }
}

@Composable
@Preview
fun ShimmerItemPreview() {
    ShimmerItem()
}
@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun ShimmerItemDarkPreview() {
    ShimmerItem()
}
