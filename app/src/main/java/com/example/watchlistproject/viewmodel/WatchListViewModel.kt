package com.example.watchlistproject.viewmodel

import androidx.lifecycle.ViewModel
import com.example.watchlistproject.data.Movie
import com.example.watchlistproject.view.WatchListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WatchListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(WatchListUiState())

    val uiState: StateFlow<WatchListUiState> =
        _uiState.asStateFlow()

    private var nextId = 1

    fun onTitleChange(newTitle: String) {
        _uiState.update {
            it.copy(
                titleInput = newTitle,
                validationMessage = null
            )
        }
    }

    fun addMovie() {
        val title = _uiState.value.titleInput.trim()

        if (title.isEmpty()) {
            _uiState.update {
                it.copy(
                    validationMessage = "Judul film atau drama tidak boleh kosong."
                )
            }
            return
        }

        val newMovie = Movie(
            id = nextId++,
            title = title,
            isWatched = false
        )

        _uiState.update {
            it.copy(
                movies = it.movies + newMovie,
                titleInput = "",
                validationMessage = null
            )
        }
    }

    fun toggleWatched(movieId: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                movies = currentState.movies.map { movie ->
                    if (movie.id == movieId) {
                        movie.copy(isWatched = !movie.isWatched)
                    } else {
                        movie
                    }
                }
            )
        }
    }

    fun deleteMovie(movieId: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                movies = currentState.movies.filterNot {
                    it.id == movieId
                }
            )
        }
    }
}
