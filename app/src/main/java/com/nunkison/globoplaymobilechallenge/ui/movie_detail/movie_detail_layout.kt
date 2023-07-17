package com.nunkison.globoplaymobilechallenge.ui.movie_detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nunkison.globoplaymobilechallenge.ui.movie_detail.data.MovieDetailData

@Composable
fun MovieDetailLayout(
    data: MovieDetailData,
    onTabSelected: (index: Int) -> Unit,
    onMovieClick: (id: String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp)
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
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
                    lineHeight = 16.sp
                )
            }
        }
        Box(
            modifier = Modifier.background(Color.Black)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(10),
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = "Assista",
                        tint = Color.DarkGray
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        text = "Assista",
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold
                    )
                }
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    border = BorderStroke(1.dp, Color.LightGray),
                    shape = RoundedCornerShape(10),
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Minha Lista",
                        tint = Color.White
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        text = "Minha Lista",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                    )
                }

            }
        }
        TabRow(selectedTabIndex = data.tabSelected,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    color = Color.White,
                    modifier = Modifier.tabIndicatorOffset(
                        currentTabPosition = tabPositions[data.tabSelected],
                    ),
                )
            },
            divider = {}
        ) {
            listOf("ASSISTA TAMBÉM", "DETALHES").forEachIndexed { index, title ->
                Tab(
                    content = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Black)
                                .padding(8.dp)
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = title,
                                color = if (data.tabSelected == index) Color.White else Color.DarkGray,
                                textAlign = TextAlign.Center
                            )
                        }
                    },
                    selected = data.tabSelected == index,
                    onClick = { onTabSelected(index) },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.Black,
                )
            }
        }
        when (data.tabSelected) {
            0 -> Box {
                Text("Assista tambem lista")
            }

            1 -> Box {
                Text("Detalhes lista")
            }
        }
    }
}
