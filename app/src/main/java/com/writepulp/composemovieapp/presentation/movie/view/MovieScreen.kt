package com.writepulp.composemovieapp.presentation.movie.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.writepulp.composemovieapp.presentation.Screen
import com.writepulp.composemovieapp.presentation.movie.MovieEvent
import com.writepulp.composemovieapp.presentation.movie.MoviesViewModel

@Composable
fun MovieScreen(
    navController: NavController,
    viewModel: MoviesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)) {
        Column {
            MovieSearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                hint = "Search",
                onSearch = {
                    viewModel.onEvent(MovieEvent.Search(it))
                }
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.movies) { movie ->
                    MovieListRow(movie = movie, onItemClick = {
                        navController.navigate(Screen.MovieDetailScreen.route + "/${movie
                            .imdbID}")
                    } )
                }
            }
        }
    }
}

@Composable
fun MovieSearchBar(modifier: Modifier = Modifier,
                   hint:String ="",
                   onSearch: (String) -> Unit) {

    var text by remember {
        mutableStateOf("")
    }

    var isHindDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        TextField(value = text, onValueChange = {
            text = it
        }, keyboardActions = KeyboardActions(onDone = {
            onSearch(text)
        }), maxLines = 1, singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(MaterialTheme.colorScheme.background),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .padding(horizontal = 12.dp)
                .background(Color.White, CircleShape)
                .onFocusChanged {
                    isHindDisplayed = it.isFocused && text.isEmpty()
                }
        )
        if (isHindDisplayed) {
            Text(text = hint, color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp))
        }
    }
}