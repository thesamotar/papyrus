package com.example.papyrus.ui.library

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun LibraryScreen(viewModel: LibraryViewModel, modifier: Modifier = Modifier) {
    val shelves by viewModel.shelves.collectAsState()
    val selectedShelfId by viewModel.selectedShelfId.collectAsState()
    val booksInShelf by viewModel.booksInShelf.collectAsState()
    var showAddShelfDialog by remember { mutableStateOf(false) }
    var newShelfName by remember { mutableStateOf("") }
    var shelfError by remember { mutableStateOf("") }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(Modifier.height(48.dp))
            Text(
                text = "Your Shelves",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            Button(
                onClick = { showAddShelfDialog = true },
                modifier = Modifier.padding(bottom = 32.dp)
            ) {
                Text("Add Shelf")
            }
        }

        // Shelves grid (rows of 2)
        items(shelves.chunked(2)) { shelfRow ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                shelfRow.forEach { shelf ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(12.dp)
                            .clickable { viewModel.selectShelf(shelf.id) }
                    ) {
                        Surface(
                            shape = RoundedCornerShape(32.dp),
                            color = if (selectedShelfId == shelf.id)
                                MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.primaryContainer,
                            shadowElevation = 8.dp,
                            modifier = Modifier.size(80.dp)
                        ) {
                            Icon(
                                getShelfIcon(shelf.name, shelf.isDefault),
                                contentDescription = shelf.name,
                                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier
                                    .size(56.dp)
                                    .padding(12.dp)
                            )
                        }
                        Text(
                            shelf.name,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
                // If only one shelf in row, add blank space for alignment
                if (shelfRow.size < 2) {
                    Spacer(modifier = Modifier.size(80.dp))
                }
            }
        }

        item {
            Spacer(Modifier.height(24.dp))
        }

        // Book list for selected shelf
        if (booksInShelf.isEmpty()) {
            item {
                Text("No books in this shelf", modifier = Modifier.padding(16.dp))
            }
        } else {
            items(booksInShelf) { book ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 16.dp),
                    shape = RoundedCornerShape(10.dp),
                    tonalElevation = 1.dp
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(book.title, style = MaterialTheme.typography.titleMedium)
                            Text(book.authors.joinToString(", "), style = MaterialTheme.typography.bodyMedium)
                            Text(book.publishedYear, style = MaterialTheme.typography.bodySmall)
                        }
                        IconButton(onClick = {
                            viewModel.removeBookFromShelf(book.id, selectedShelfId)
                        }) {
                            Icon(Icons.Filled.Delete, contentDescription = "Remove")
                        }
                    }
                }
            }
        }
    }

    // Add Shelf dialog
    if (showAddShelfDialog) {
        AlertDialog(
            onDismissRequest = { showAddShelfDialog = false; shelfError = "" },
            confirmButton = {
                Button(onClick = {
                    val exists = shelves.any { it.name.equals(newShelfName.trim(), ignoreCase = true) }
                    if (newShelfName.isBlank()) {
                        shelfError = "Shelf name cannot be blank."
                    } else if (exists) {
                        shelfError = "Shelf with this name already exists."
                    } else {
                        viewModel.addCustomShelf(newShelfName.trim())
                        newShelfName = ""
                        shelfError = ""
                        showAddShelfDialog = false
                    }
                }) { Text("Create Shelf") }
            },
            dismissButton = {
                TextButton(onClick = { showAddShelfDialog = false; shelfError = "" }) { Text("Cancel") }
            },
            title = { Text("New Shelf") },
            text = {
                Column {
                    OutlinedTextField(
                        value = newShelfName,
                        onValueChange = { newShelfName = it },
                        placeholder = { Text("Shelf name") }
                    )
                    if (shelfError.isNotBlank()) {
                        Text(shelfError, color = MaterialTheme.colorScheme.error)
                    }
                }
            }
        )
    }
}

@Composable
fun getShelfIcon(label: String, isDefault: Boolean): ImageVector =
    when {
        isDefault && label == "To Be Read" -> Icons.Filled.Bookmark
        isDefault && label == "Currently Reading" -> Icons.Filled.AutoStories
        isDefault && label == "Read" -> Icons.Filled.Done
        isDefault && label == "Want to Read" -> Icons.Filled.Star
        else -> Icons.Filled.Book // For custom shelves
    }