package com.example.watchlistproject.data

data class Movie(
    val id: Int,
    val title: String,
    val isWatched: Boolean = false
)