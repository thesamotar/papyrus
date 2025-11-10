package com.example.papyrus.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.papyrus.data.BookItem
import com.example.papyrus.data.GoogleBooksService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.*

class SearchViewModel : ViewModel() {
    private val _results = MutableStateFlow<List<BookItem>>(emptyList())
    val results: StateFlow<List<BookItem>> = _results

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading
    private var searchJob: Job? = null

    fun search(query: String) {
        //searchJob?.cancel()
        //only search for non-blank queries
        if (query.isBlank()) {
            _results.value = emptyList()
            _loading.value = false
            return
        }

        _loading.value = true
        viewModelScope.launch {
            delay(1000)
            val startTime = System.currentTimeMillis()
            try {
                val response = GoogleBooksService.api.searchBooks(query)
                val duration = System.currentTimeMillis() - startTime
                println("$duration")
                _results.value = response.items ?: emptyList()
            } catch (e: Exception) {
                println("Search Error: ${e.message}")
                _results.value = emptyList()
            }
            _loading.value = false
        }
    }
}