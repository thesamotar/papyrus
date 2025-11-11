package com.example.papyrus.data.LibraryManagement

data class Book(
    val id: String,
    val title: String,
    val authors: List<String>,
    val publishedYear: String
)

data class Shelf(
    val id: String,      // Unique identifier (could be UUID, or "default-x")
    val name: String,
    val isDefault: Boolean = false,
    val bookIds: MutableList<String> = mutableListOf()
)

object DefaultShelves {
    val shelves = listOf(
        Shelf(id = "to_be_read", name = "To Be Read", isDefault = true),
        Shelf(id = "currently_reading", name = "Currently Reading", isDefault = true),
        Shelf(id = "read", name = "Read", isDefault = true),
        Shelf(id = "want_to_read", name = "Want to Read", isDefault = true)
    )
}