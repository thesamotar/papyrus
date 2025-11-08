package com.example.papyrus.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.focus.onFocusChanged

/**
 * SearchTopBar
 * Replaces default app bar (Papyrus heading) with a modern search bar and notification bell.
 * Can be placed as the topBar slot of your Scaffold.
 */
@Composable
fun SearchTopBar(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onSearchActivated: () -> Unit,
    onNotificationsClicked: () -> Unit
) {
    // Top app bar surface with elevation and color matching theme
    Surface(
        shadowElevation = 6.dp,
        tonalElevation = 6.dp,
        color = MaterialTheme.colorScheme.surface,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding(),
                    start = 12.dp,
                    end = 12.dp,
                    bottom = 8.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // ----- Search Bar -----
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                TextField(
                    value = searchText,
                    onValueChange = onSearchTextChange,
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "Search", tint = MaterialTheme.colorScheme.primary)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        //.height(44.dp)
                        .background(
                            color = MaterialTheme.colorScheme.background,
                            shape = RoundedCornerShape(24.dp)
                        )
                    .onFocusChanged { focusState ->
                        if (focusState.isFocused) {
                            onSearchActivated()
                        }
                },
                        //.clickable { onSearchActivated() }, // Activates search UI
                    shape = RoundedCornerShape(24.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.background,
                        focusedContainerColor = MaterialTheme.colorScheme.background,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedTextColor = MaterialTheme.colorScheme.onBackground,
                        unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                        focusedPlaceholderColor = Color.Gray,
                        unfocusedPlaceholderColor = Color.Gray,
                    ),
                    placeholder = { Text("Search by title, author or ISBN", style = MaterialTheme.typography.bodyLarge) },
                    singleLine = true
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // ----- Notifications Bell Icon -----
            IconButton(
                onClick = onNotificationsClicked
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}