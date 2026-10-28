package com.example.watchlistproject.view

import com.example.watchlistproject.data.Movie

data class WatchListUiState(
    val movies: List<Movie> = emptyList(),
    val titleInput: String = "",
    val validationMessage: String? = null
)