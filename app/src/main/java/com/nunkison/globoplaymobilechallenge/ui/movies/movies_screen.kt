package com.nunkison.globoplaymobilechallenge.ui.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nunkison.globoplaymobilechallenge.R
import com.nunkison.globoplaymobilechallenge.ui.theme.GloboplayMobileChallengeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        colors = TopAppBarDefaults.mediumTopAppBarColors(
                            containerColor = Color.Transparent,
                        ),
                        title = {
                            Text(
                                stringResource(id = R.string.app_name),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                    )
                },
                content = { innerPadding ->
                    LazyColumn(
                        contentPadding = innerPadding,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val list = (0..75).map { it.toString() }
                        items(count = list.size) {
                            Text(
                                text = list[it],
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            )
                        }
                    }
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesScreenPreview() {
    GloboplayMobileChallengeTheme {
        MoviesScreen(rememberNavController())
    }
}