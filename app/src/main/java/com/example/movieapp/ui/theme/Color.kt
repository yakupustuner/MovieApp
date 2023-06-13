package com.example.movieapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Calendar = Color(0xFFAB09F1)
val Actors = Color(0xFFF10924)
val Location = Color(0xFFF18C09)
val Director = Color(0xFF03FC8C)
val Rating = Color(0xFFFCE703)


val LightGray = Color(0xFFD8D8D8)
val DarkGray = Color(0xFF2A2A2A)

val ShimmerLightGray = Color(0xFFF1F1F1)
val ShimmerMediumGray = Color(0xFFE3E3E3)
val ShimmerDarkGray = Color(0xFF1D1D1D)





val Colors.statusBarColor
    get() = if (isLight) Purple80 else Color.Black

val Colors.titleColor
    get() = if (isLight) DarkGray else LightGray
val Colors.welcomeScreenBackground
    get() = if (isLight) Color.White else Color.Black

val Colors.activeIndicatorColor
    get() = if (isLight)  Purple40 else Purple80

val Colors.inactiveIndicatorColor
    get() = if (isLight) LightGray else DarkGray

val Colors.buttonBackgroundColor
    get() = if (isLight) Purple40 else Purple80

val Colors.appBarBackgroundColor: Color
    get() = if (isLight) Purple40 else Color.Black

val Colors.appBarContentColor: Color
    get() = if (isLight) Color.White else LightGray

val Colors.topAppBarContentColor: Color
    get() = if (isLight) Color.White else LightGray