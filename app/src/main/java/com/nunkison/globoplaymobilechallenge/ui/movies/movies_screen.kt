package com.nunkison.globoplaymobilechallenge.ui.movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nunkison.globoplaymobilechallenge.R
import com.nunkison.globoplaymobilechallenge.ui.movies.data.MoviesViewModel
import com.nunkison.globoplaymobilechallenge.ui.movies.data.moviesScreenData
import com.nunkison.globoplaymobilechallenge.ui.theme.GloboplayMobileChallengeTheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(navController: NavHostController) {
    val vm = koinViewModel<MoviesViewModel>()
    when (val state = vm.uiState.collectAsState().value){
        is MoviesViewModel.UiState.Success -> MoviesLayout(data = state.data)
        is MoviesViewModel.UiState.Error -> ErrorLayout(data = state.message)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                Surface(shadowElevation = 4.dp) {
                    CenterAlignedTopAppBar(
                        modifier = Modifier.background(Color.Black),
                        colors = TopAppBarDefaults.mediumTopAppBarColors(
                            containerColor = Color.Black,
                        ),
                        title = {
                            Image(
                                painter = painterResource(id = R.drawable.logo_globoplay_white),
                                contentDescription = stringResource(id = R.string.app_name),
                                modifier = Modifier.height(100.dp)
                            )
                        },
                    )
                }
            },
            content = { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    
                }
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesScreenPreview() {
    GloboplayMobileChallengeTheme {
        MoviesScreen(rememberNavController())
    }
}