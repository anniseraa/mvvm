package com.example.watchlistproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.watchlistproject.ui.theme.WatchListProjectTheme
import com.example.watchlistproject.view.WatchListScreen
import com.example.watchlistproject.viewmodel.WatchListViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            WatchListProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WatchListApp()
                }
            }
        }
    }
}

@Composable
fun WatchListApp(
    watchListViewModel: WatchListViewModel = viewModel()
) {
    val uiState by watchListViewModel.uiState
        .collectAsStateWithLifecycle()

    WatchListScreen(
        uiState = uiState,
        onTitleChange = watchListViewModel::onTitleChange,
        onAddMovie = watchListViewModel::addMovie,
        onToggleWatched = watchListViewModel::toggleWatched,
        onDeleteMovie = watchListViewModel::deleteMovie
    )
}