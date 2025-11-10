package com.example.papyrus.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GoogleBooksService {
    val api: GoogleBooksApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/books/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GoogleBooksApi::class.java)
    }
}