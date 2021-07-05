package com.maslima.globo_play_recrutamento.presentation.ui.movie_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.maslima.globo_play_recrutamento.R
import com.maslima.globo_play_recrutamento.utils.loadDrawableImage
import com.maslima.globo_play_recrutamento.utils.loadPictures
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ScreenDetail()
            }
        }
    }
}

@Composable
fun ScreenDetail() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "globoplay")
                },
                navigationIcon = {
                    Icon(Icons.Filled.ArrowBack)
                },
                elevation = Dp(7f),
            )
        },
        bodyContent = {
            MovieContent()
        },
    )
}

@Composable
fun MovieContent() {
    ScrollableColumn {
        Column {
            ImageSection()
            MovieDescriptionSection()
            RowButtons()

        }
    }
}

@Composable
private fun ImageSection() {
    val bitmap = loadPictures(url = "", defaultImage = R.drawable.no_image_avaiable)
    bitmap.value?.let {
        Image(
            bitmap = it.asImageBitmap(),
            modifier = Modifier
                .fillMaxWidth()
                .preferredHeight(260.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun MovieDescriptionSection() {
    Text(
        text = "Orgulho e Paixão",
        style = MaterialTheme.typography.h5,
        modifier = Modifier
            .padding(bottom = 4.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )
    Text(
        text = "Novela",
        style = MaterialTheme.typography.subtitle2,
        modifier = Modifier
            .padding(bottom = 4.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )
    Text(
        text = "Orgulho e paixão tem seus personagens livremente inspirados no universo da escritora inglesa Jane austen. É uma história romância",
        style = MaterialTheme.typography.body1,
        modifier = Modifier
            .padding(bottom = 4.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun RowButtons() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { /*TODO*/ },
            Modifier
                .preferredHeight(40.dp)
                .wrapContentWidth()
        ) {
            val img = loadDrawableImage(defaultImage = R.drawable.ic_play_arrow)
            img.value?.let {
                Image(bitmap = it.asImageBitmap())
            }
            Text(text = "Assista")
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = { /*TODO*/ },
            Modifier
                .preferredHeight(40.dp)
                .wrapContentWidth()
        ) {
            val img = loadDrawableImage(defaultImage = R.drawable.ic_star_rate)
            img.value?.let {
                Image(bitmap = it.asImageBitmap())
            }
            Text(text = "Favoritos")
        }
    }
}
