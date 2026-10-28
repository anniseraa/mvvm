package com.example.watchlistproject.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.watchlistproject.data.Movie
import com.example.watchlistproject.ui.theme.WatchListTheme

@Composable
fun WatchListScreen(
    uiState: WatchListUiState,
    onTitleChange: (String) -> Unit,
    onAddMovie: () -> Unit,
    onToggleWatched: (Int) -> Unit,
    onDeleteMovie: (Int) -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val isWideScreen = maxWidth >= 600.dp

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Surface(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .statusBarsPadding()
                            .padding(horizontal = 20.dp, vertical = 16.dp)
                    ) {
                        Text(
                            text = "WatchList 🍿",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Text(
                            text = "Simpan film dan drama favoritmu",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        ) { innerPadding ->

            val contentModifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)

            if (isWideScreen) {
                Row(
                    modifier = contentModifier,
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                    ) {
                        MovieInputForm(
                            uiState = uiState,
                            onTitleChange = onTitleChange,
                            onAddMovie = onAddMovie
                        )
                    }

                    MovieList(
                        movies = uiState.movies,
                        onToggleWatched = onToggleWatched,
                        onDeleteMovie = onDeleteMovie,
                        modifier = Modifier
                            .weight(1.5f)
                            .fillMaxHeight()
                    )
                }
            } else {
                Column(
                    modifier = contentModifier
                ) {
                    MovieInputForm(
                        uiState = uiState,
                        onTitleChange = onTitleChange,
                        onAddMovie = onAddMovie
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    MovieList(
                        movies = uiState.movies,
                        onToggleWatched = onToggleWatched,
                        onDeleteMovie = onDeleteMovie,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun MovieInputForm(
    uiState: WatchListUiState,
    onTitleChange: (String) -> Unit,
    onAddMovie: () -> Unit
) {
    Text(
        text = "Tambah Film atau Drama",
        style = MaterialTheme.typography.titleLarge
    )

    Spacer(modifier = Modifier.height(12.dp))

    OutlinedTextField(
        value = uiState.titleInput,
        onValueChange = onTitleChange,
        label = { Text("Judul film atau drama") },
        placeholder = { Text("Contoh: Hidden Love") },
        singleLine = true,
        isError = uiState.validationMessage != null,
        modifier = Modifier.fillMaxWidth()
    )

    if (uiState.validationMessage != null) {
        Text(
            text = uiState.validationMessage,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 4.dp)
        )
    }

    Spacer(modifier = Modifier.height(8.dp))

    Button(
        onClick = onAddMovie,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Tambah ke WatchList")
    }
}

@Composable
private fun MovieList(
    movies: List<Movie>,
    onToggleWatched: (Int) -> Unit,
    onDeleteMovie: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Daftar Tontonan (${movies.size})",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (movies.isEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Belum ada film atau drama. Yuk, tambahkan!")
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(movies, key = { it.id }) { movie ->
                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = movie.title,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = if (movie.isWatched)
                                    "Sudah ditonton ✓"
                                else
                                    "Belum ditonton",
                                color = if (movie.isWatched)
                                    MaterialTheme.colorScheme.primary
                                else
                                    MaterialTheme.colorScheme.secondary,
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                OutlinedButton(
                                    onClick = { onToggleWatched(movie.id) },
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        if (movie.isWatched)
                                            "Belum ditonton"
                                        else
                                            "Tandai ditonton"
                                    )
                                }

                                Spacer(modifier = Modifier.width(2.dp))

                                OutlinedButton(
                                    onClick = { onDeleteMovie(movie.id) },
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text("Hapus")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640, name = "Layar Sempit (Portrait)")
@Composable
private fun WatchListScreenNarrowPreview() {
    WatchListTheme {
        WatchListScreen(
            uiState = WatchListUiState(
                movies = listOf(
                    Movie(1, "Hidden Love", isWatched = true),
                    Movie(2, "Lovely Runner", isWatched = false)
                )
            ),
            onTitleChange = {},
            onAddMovie = {},
            onToggleWatched = {},
            onDeleteMovie = {}
        )
    }
}

@Preview(showBackground = true, widthDp = 720, heightDp = 360, name = "Layar Lebar (Landscape)")
@Composable
private fun WatchListScreenWidePreview() {
    WatchListTheme {
        WatchListScreen(
            uiState = WatchListUiState(
                movies = listOf(
                    Movie(1, "Hidden Love", isWatched = true),
                    Movie(2, "Lovely Runner", isWatched = false)
                )
            ),
            onTitleChange = {},
            onAddMovie = {},
            onToggleWatched = {},
            onDeleteMovie = {}
        )
    }
}

