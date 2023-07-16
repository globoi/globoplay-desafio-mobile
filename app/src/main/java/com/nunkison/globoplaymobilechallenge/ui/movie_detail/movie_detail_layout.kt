package com.nunkison.globoplaymobilechallenge.ui.movie_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nunkison.globoplaymobilechallenge.ui.movie_detail.data.MovieDetailData
import com.nunkison.globoplaymobilechallenge.ui.theme.GloboplayMobileChallengeTheme

@Composable
fun MovieDetailLayout(
    data: MovieDetailData,
    onMovieClick: (id: String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .blur(19.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data.coverPath)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .align(Alignment.BottomStart)
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black
                                    ),
                                    startY = 0.0f,
                                    endY = 500.0f
                                )
                            )
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .background(Color.Black)
                    )
                }
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp)
            ) {
                Box(modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                ){
                    AsyncImage(
                        modifier = Modifier
                            .width(150.dp),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(data.coverPath)
                            .crossfade(true)
                            .build(),
                        contentDescription = data.name,
                        contentScale = ContentScale.FillWidth,
                    )
                }
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp),
                    text = data.name,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 8.dp),
                    text = data.category,
                    fontSize = 12.sp,
                )
                Text(
                    modifier = Modifier
                        .padding(
                            top = 8.dp,
                            start = 16.dp,
                            end = 16.dp
                        ),
                    text = data.description,
                    fontSize = 14.sp,
                )
            }
        }
    }
}