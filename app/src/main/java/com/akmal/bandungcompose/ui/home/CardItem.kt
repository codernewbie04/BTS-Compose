package com.akmal.bandungcompose.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.akmal.bandungcompose.injection.Injection
import com.akmal.bandungcompose.model.TempatWisata
import com.akmal.bandungcompose.ui.ViewModelFactory
import com.akmal.bandungcompose.ui.common.UiState
import com.akmal.bandungcompose.ui.theme.Black

@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getAllDestination()
                }
                is UiState.Success -> {
                    ContainerCard(
                        modifier = modifier,
                        tempatWisata = uiState.data,
                        navigateToDetail = navigateToDetail,
                    )
                }
                is UiState.Error -> {}
            }
        }
    }
}

@Composable
fun ContainerCard(
    tempatWisata: List<TempatWisata>,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    //Kriteria 1
    LazyColumn{
        items(tempatWisata) { item ->
            CardViewComponent(
                data = item,
                modifier = modifier.clickable {
                    navigateToDetail(item.id.toString())
                }
            )
        }
    }
}

@Composable
fun CardViewComponent(
    modifier: Modifier = Modifier,
    data: TempatWisata
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp),
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(shape = RoundedCornerShape(4.dp))
            ) {
                DestinationImage(data)
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = data.name.toString(),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Black
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = data.address.toString(),
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Black
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun DestinationImage(tempatWisata: TempatWisata) {
    val painter = rememberImagePainter(
        data = tempatWisata.img!!,
        builder = {
            transformations(CircleCropTransformation())
            // Add any other desired image transformations here
        }
    )

    Image(
        painter = painter,
        contentDescription = tempatWisata.name,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
    )
}

@Composable
fun MyTopBarApp(
    onMenuClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text("Bandung")
        },
        actions = {
            IconButton(onClick = {
                onMenuClick()
            },
            ) {
                Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "about_page")
            }
        }
    )
}