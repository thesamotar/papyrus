package com.example.papyrus.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

// Added as part of custom UI coloring
val PapyrusLightYellow = Color(0xFFFFF8E1)   // Light yellow, old paper
val PapyrusBrown      = Color(0xFF795548)   // Classic book spine brown
val PapyrusYellowOchre = Color(0xFFFFD966)  // Yellow ochre accent


val PapyrusLightColorScheme = lightColorScheme(
    primary = PapyrusBrown,
    onPrimary = PapyrusLightYellow,
    secondary = PapyrusYellowOchre,
    background = PapyrusLightYellow,
    onBackground = PapyrusBrown,
    surface = PapyrusLightYellow,
    onSurface = PapyrusBrown,
    tertiary = PapyrusYellowOchre
)

val PapyrusDarkColorScheme = darkColorScheme(
    primary = PapyrusYellowOchre,
    onPrimary = PapyrusBrown,
    background = PapyrusBrown,
    onBackground = PapyrusYellowOchre,
    surface = PapyrusBrown,
    onSurface = PapyrusYellowOchre,
    secondary = PapyrusLightYellow
)