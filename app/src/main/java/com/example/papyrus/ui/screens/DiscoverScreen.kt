package com.example.papyrus.ui.screens

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp

/**
 * DiscoverScreen will let users search for new books and get recommendations.
 * For now you'll show a placeholder message, then later add search bar and filters.
 */
@Composable
fun DiscoverScreen(modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        Text(
            text = "Discover new reads and recommendations.",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(24.dp)
        )
    }
}