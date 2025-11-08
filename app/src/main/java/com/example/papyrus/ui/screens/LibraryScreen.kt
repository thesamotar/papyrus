package com.example.papyrus.ui.screens

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp

/**
 * LibraryScreen is where users manage their book shelves and books.
 * You'll add shelf management, book lists, and related features here later.
 */
@Composable
fun LibraryScreen(modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        Text(
            text = "Your library: manage shelves & books.",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(24.dp)
        )
    }
}