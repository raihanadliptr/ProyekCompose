package com.example.proyekcomposehantop.ui.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.proyekcomposehantop.data.PersonRepository
import com.example.proyekcomposehantop.ui.screen.ViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyekcomposehantop.ui.common.UiState
import com.example.proyekcomposehantop.R
import com.example.proyekcomposehantop.ui.theme.ProyekComposeHantopTheme

@Composable
fun DetailScreen(
    id: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(PersonRepository())
    ),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getPersonById(id)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.name,
                    data.hobby,
                    data.birthday,
                    data.domicile,
                    data.photo,
                    onBackClick = navigateBack
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    name: String,
    hobby: String,
    birthday: String,
    domicile: String,
    photo: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                AsyncImage(
                    model = photo,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(10.dp)
                        .height(300.dp)
                        .clip(CircleShape)
                )
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(
                    text = name,
                    color = MaterialTheme.colors.primary,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
            }
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.hobby),
                    color = MaterialTheme.colors.secondary,
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Text(
                    text = hobby,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Justify,
                )
                Text(
                    text = stringResource(R.string.birthday),
                    color = MaterialTheme.colors.secondary,
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Text(
                    text = birthday,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Justify,
                )
                Text(
                    text = stringResource(R.string.domicile),
                    color = MaterialTheme.colors.secondary,
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Text(
                    text = domicile,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Justify,
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    ProyekComposeHantopTheme() {
        DetailContent(
            name = "Raihan Adliputra",
            hobby = "Running",
            birthday = "01 October 2022",
            domicile = "Jakarta",
            photo = "https://drive.google.com/file/d/1dVfRG2borp-BcsBSfKtDSMfK49dA9WNL/view?usp=sharing",
            onBackClick = {}
        )
    }
}