package com.akmal.bandungcompose.ui.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.akmal.bandungcompose.injection.Injection
import com.akmal.bandungcompose.model.TempatWisata
import com.akmal.bandungcompose.ui.ViewModelFactory
import com.akmal.bandungcompose.ui.common.UiState

@Composable
fun DetailScreen(
    idDetail: String,
    navigateBack: () -> Unit,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
){
    val scrollState = rememberScrollState()

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getIdDestination(idDetail)
            }
            is UiState.Success -> {
                val data = uiState.data
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    BoxWithConstraints(modifier = Modifier.weight(1f)) {
                        Surface {
                            Column (
                                modifier = Modifier
                                    .fillMaxSize()
                                    .verticalScroll(scrollState)
                            ) {
                                Header(
                                    scrollState = scrollState,
                                    tempatWisata = data,
                                    onBackClick = navigateBack
                                )
                                Body(
                                    tempatWisata = data
                                )
                            }
                        }
                    }
                }
            }
            is UiState.Error -> {

            }
        }
    }
}
@Composable
private fun Header(
    scrollState: ScrollState,
    tempatWisata: TempatWisata,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    val offset = scrollState.value / 2
    val offsetDp = with(LocalDensity.current) { offset.toDp() }

    Box {
        DestinationImage(tempatWisata)
        Box(
            modifier = modifier
                .statusBarsPadding()
                .fillMaxWidth()
        ) {
            BackButton(
                imageVector = Icons.Default.ArrowBack,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp),
                clickListener = onBackClick
            )
        }
    }
}

//Kriteria 2
@Composable
private fun Body(tempatWisata: TempatWisata) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(8.dp))
        Name(tempatWisata, modifier = Modifier)
        Label(name = "Buka", modifier = Modifier)
        Open(tempatWisata, modifier = Modifier)
        Label(name = "Alamat", modifier = Modifier)
        Address(tempatWisata, modifier = Modifier)
        Label(name = "Deskripsi", modifier = Modifier)
        Description(tempatWisata, modifier = Modifier)
    }
}


@Composable
private fun Label(name: String, modifier: Modifier) {
    Text(
        text = name,
        modifier = modifier.padding(16.dp, 4.dp),
        fontWeight = FontWeight.Bold,
    )
}

@Composable
private fun Name(tempatWisata: TempatWisata, modifier: Modifier = Modifier) {
    Text(
        text = tempatWisata.name.toString(),
        modifier = modifier.padding(16.dp, 0.dp, 16.dp, 16.dp),
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
private fun Address(tempatWisata: TempatWisata, modifier: Modifier = Modifier) {
    Text(
        text = tempatWisata.address.toString(),
        modifier = modifier.padding(16.dp, 0.dp),
        style = MaterialTheme.typography.caption,
    )
}

@Composable
private fun Open(tempatWisata: TempatWisata, modifier: Modifier = Modifier) {
    Text(
        text = tempatWisata.open.toString(),
        modifier = modifier.padding(16.dp, 0.dp),
        style = MaterialTheme.typography.caption,
    )
}

@Composable
private fun Description(tempatWisata: TempatWisata, modifier: Modifier = Modifier) {
    Text(
        text = tempatWisata.description.toString(),
        modifier = modifier.padding(16.dp, 0.dp, 16.dp, 16.dp),
        style = MaterialTheme.typography.caption,
    )
}

@Composable
private fun DestinationImage(tempatWisata: TempatWisata) {
    val painter = rememberImagePainter(
        data = tempatWisata.img!!,
    )

    Image(
        painter = painter,
        contentDescription = tempatWisata.name,
        modifier = Modifier
            .padding(0.dp, 8.dp, 0.dp, 0.dp)
            .fillMaxWidth()
            .height(200.dp)
    )
}

@Composable
fun BackButton(imageVector: ImageVector, modifier: Modifier, clickListener: () -> Unit) {
    Button(
        onClick = { clickListener() },
        border = BorderStroke(2.dp, Color(0xFF00001a)),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFE1E1E1),
            contentColor = Color.Black,
            disabledBackgroundColor = Color.Gray,
            disabledContentColor = Color.LightGray
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.elevation(4.dp),
        modifier = modifier.size(48.dp)
    ) {
        Icon(imageVector = imageVector, contentDescription = "Back")
    }
}

