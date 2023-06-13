package com.example.movieapp.domain.model

import androidx.annotation.DrawableRes
import com.example.movieapp.R

sealed class OnBoardingPage(
    @DrawableRes
    val image:Int,

    ){
    object First : OnBoardingPage(
        image = R.drawable.welcome
    )

    object Second : OnBoardingPage(
        image = R.drawable.areyouready
    )


}
