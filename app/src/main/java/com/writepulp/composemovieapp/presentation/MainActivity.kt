package com.writepulp.composemovieapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.writepulp.composemovieapp.datautil.Constants.IMDB_ID
import com.writepulp.composemovieapp.presentation.detail.view.MovieDetailScreen
import com.writepulp.composemovieapp.presentation.movie.view.MovieScreen
import com.writepulp.composemovieapp.presentation.theme.ui.ComposeMovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeMovieAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.MovieScreen.route) {

                        composable(route = Screen.MovieScreen.route){
                            MovieScreen(navController = navController)
                        }

                        composable(route = Screen.MovieDetailScreen.route + "/{${IMDB_ID}}"){
                            MovieDetailScreen()
                        }

                    }
                }
            }
        }
    }
}
