package com.example.papyrus.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.foundation.isSystemInDarkTheme

@Composable
fun PapyrusTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) PapyrusDarkColorScheme else PapyrusLightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = PapyrusTypography,
        shapes = Shapes,
        content = content
    )
}