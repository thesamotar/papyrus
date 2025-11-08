package com.example.papyrus.ui.screens

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp

/**
 * CommunityScreen will someday show social/community features.
 * For now, just a "coming soon" message.
 */
@Composable
fun CommunityScreen(modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        Text(
            text = "Community features coming soon.",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(24.dp)
        )
    }
}