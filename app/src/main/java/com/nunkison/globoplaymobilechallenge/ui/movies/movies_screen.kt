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
import com.nunkison.globoplaymobilechallenge.ui.theme.GloboplayMobileChallengeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(navController: NavHostController) {
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
                    LazyColumn {
                        itemsIndexed(moviesScreenData) {index, moviesGroup ->
                            Text(
                                modifier = Modifier
                                    .padding(
                                        top = if (index == 0) 40.dp else 24.dp,
                                        bottom = 16.dp,
                                        start = 16.dp,
                                    ),
                                text = moviesGroup.category,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            LazyRow {
                                itemsIndexed(moviesGroup.movieCovers) { index, item ->
                                    Box(
                                        modifier = Modifier
                                            .width(if (index == 0 || index == moviesGroup.movieCovers.size - 1) 158.dp else 150.dp)
                                            .padding(
                                                start = if (index == 0) 16.dp else 8.dp,
                                                end = if (index == moviesGroup.movieCovers.size - 1) 16.dp else 8.dp
                                            )
                                    ) {
                                        AsyncImage(
                                            modifier = Modifier
                                                .width(150.dp),
                                            model = ImageRequest.Builder(LocalContext.current)
                                                .data(item.cover)
                                                .crossfade(true)
                                                .build(),
                                            contentDescription = item.name,
                                            contentScale = ContentScale.FillWidth,

                                            )
                                    }
                                }
                            }
                            Box(modifier = Modifier.height(30.dp))
                        }
                    }

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