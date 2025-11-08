package com.example.papyrus.ui.screens

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp

/**
 * ProfileScreen is where users will see their account,
 * medals earned, and reading challenge progress.
 */
@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        Text(
            text = "Your profile, medals, and reading challenge.",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(24.dp)
        )
    }
}