package com.example.papyrus.data.LibraryManagement

import java.util.UUID

class LibraryRepository {
    // Map shelf ID to Shelf. Mutable for easy demo.
    private val shelves: MutableMap<String, Shelf> = DefaultShelves.shelves.map { it.id to it.copy() }.toMap().toMutableMap()
    // Map book ID to Book
    private val books: MutableMap<String, Book> = mutableMapOf()

    fun getShelves(): List<Shelf> = shelves.values.toList()
    fun getShelf(shelfId: String): Shelf? = shelves[shelfId]
    fun getBooksInShelf(shelfId: String): List<Book> =
        shelves[shelfId]?.bookIds?.mapNotNull { books[it] } ?: emptyList()

    fun addCustomShelf(name: String): Shelf {
        val newShelf = Shelf(id = UUID.randomUUID().toString(), name = name, isDefault = false)
        shelves[newShelf.id] = newShelf
        return newShelf
    }

    fun renameShelf(shelfId: String, newName: String) {
        shelves[shelfId]?.let { shelf ->
            shelves[shelfId] = shelf.copy(name = newName)
        }
    }

    fun deleteShelf(shelfId: String) {
        if (shelves[shelfId]?.isDefault == true) return // Cannot delete default
        shelves.remove(shelfId)
    }

    fun addBook(book: Book) {
        books[book.id] = book
    }

    fun addBookToShelf(book: Book, shelfId: String) {
        addBook(book)
        shelves[shelfId]?.bookIds?.let { bookIds ->
            if (!bookIds.contains(book.id)) bookIds.add(book.id)
        }
    }

    fun moveBookToShelf(book: Book, fromShelfId: String, toShelfId: String) {
        shelves[fromShelfId]?.bookIds?.remove(book.id)
        addBookToShelf(book, toShelfId)
    }

    fun removeBookFromShelf(bookId: String, shelfId: String) {
        shelves[shelfId]?.bookIds?.remove(bookId)
    }

    fun getBook(bookId: String): Book? = books[bookId]
}