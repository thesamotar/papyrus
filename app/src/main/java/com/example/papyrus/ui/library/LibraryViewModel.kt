package com.example.papyrus.ui.library

import androidx.lifecycle.ViewModel
import com.example.papyrus.data.LibraryManagement.LibraryRepository
import com.example.papyrus.data.LibraryManagement.Book
import com.example.papyrus.data.LibraryManagement.Shelf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LibraryViewModel : ViewModel() {
    private val repository = LibraryRepository()

    private val _shelves = MutableStateFlow<List<Shelf>>(repository.getShelves())
    val shelves: StateFlow<List<Shelf>> = _shelves

    private val _selectedShelfId = MutableStateFlow(repository.getShelves().first().id)
    val selectedShelfId: StateFlow<String> = _selectedShelfId

    private val _booksInShelf = MutableStateFlow<List<Book>>(emptyList())
    val booksInShelf: StateFlow<List<Book>> = _booksInShelf

    init {
        updateBooksInShelf()
    }

    fun selectShelf(shelfId: String) {
        _selectedShelfId.value = shelfId
        updateBooksInShelf()
    }

    fun addCustomShelf(name: String) {
        repository.addCustomShelf(name)
        _shelves.value = repository.getShelves()
    }

    fun renameShelf(shelfId: String, newName: String) {
        repository.renameShelf(shelfId, newName)
        _shelves.value = repository.getShelves()
    }

    fun deleteShelf(shelfId: String) {
        repository.deleteShelf(shelfId)
        _shelves.value = repository.getShelves()
        // Select a remaining shelf if current one is deleted
        if (!_shelves.value.any { it.id == _selectedShelfId.value }) {
            selectShelf(_shelves.value.firstOrNull()?.id ?: "")
        }
    }

    fun addBookToShelf(book: Book, shelfId: String) {
        repository.addBookToShelf(book, shelfId)
        updateBooksInShelf()
    }

    fun moveBookToShelf(book: Book, fromShelfId: String, toShelfId: String) {
        repository.moveBookToShelf(book, fromShelfId, toShelfId)
        updateBooksInShelf()
    }

    fun removeBookFromShelf(bookId: String, shelfId: String) {
        repository.removeBookFromShelf(bookId, shelfId)
        updateBooksInShelf()
    }

    private fun updateBooksInShelf() {
        _booksInShelf.value = repository.getBooksInShelf(_selectedShelfId.value)
    }
}