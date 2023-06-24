package com.akmal.bandungcompose.ui.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.akmal.bandungcompose.R
import com.akmal.bandungcompose.model.TempatWisata
import com.akmal.bandungcompose.ui.theme.BandungComposeTheme

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        AboutMe()
    }
}

//Kriteria 3
@Composable
fun AboutMe(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularImage()
        Text(
            text = "Akmal Muhamad Firdaus",
            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "akmalmf007@gmail.com",
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "https://akmalmf.my.id",
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colors.secondary
        )
    }
}


@Composable
private fun CircularImage() {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(200.dp)
            .clip(CircleShape)
    ) {
        Image(
            painter = painterResource(R.drawable.me),
            contentDescription = "Akmal",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}
