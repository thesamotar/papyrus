package com.example.papyrus.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.papyrus.data.GoogleBooksSearch.BookItem
import com.example.papyrus.ui.search.SearchViewModel

@Composable
fun SearchScreen(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearchExit: () -> Unit,
    viewModel: SearchViewModel
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val results by viewModel.results.collectAsState()
    val loading by viewModel.loading.collectAsState()

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = {
                onQueryChange(it)
                viewModel.search(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            placeholder = { Text("Type title, author, or ISBN") },
            singleLine = true
        )
        Spacer(Modifier.height(24.dp))

        when {
            loading -> {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
            results.isEmpty() && query.isNotBlank() -> {
                Text(
                    "No books found.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
            else -> {
                LazyColumn {
                    items(results) { book: BookItem ->
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            shape = RoundedCornerShape(12.dp),
                            tonalElevation = 1.dp
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    book.volumeInfo.title ?: "",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    book.volumeInfo.authors?.joinToString(", ") ?: "",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    book.volumeInfo.publishedDate ?: "",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { onSearchExit() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Close Search")
        }
    }
}