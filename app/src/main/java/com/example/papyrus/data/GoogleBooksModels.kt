package com.example.papyrus.data

data class GoogleBooksResponse(
    val items: List<BookItem>? = null
)

data class BookItem(
    val id: String,
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val title: String? = "",
    val authors: List<String>? = emptyList(),
    val publishedDate: String? = "",
    val imageLinks: ImageLinks? = null
)

data class ImageLinks(
    val thumbnail: String? = null
)