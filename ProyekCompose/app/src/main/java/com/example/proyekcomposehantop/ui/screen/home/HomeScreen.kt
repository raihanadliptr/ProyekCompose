package com.example.proyekcomposehantop.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.proyekcomposehantop.data.PersonRepository
import com.example.proyekcomposehantop.ui.common.UiState
import com.example.proyekcomposehantop.ui.screen.ViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyekcomposehantop.model.Person
import com.example.proyekcomposehantop.navigation.Screen
import com.example.proyekcomposehantop.ui.components.Item

@Composable
fun HomeScreen (
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(PersonRepository())),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllPersons()
            }
            is UiState.Success -> {
                HomeContent(persons = uiState.data, navigateToDetail = navigateToDetail)
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    persons: List<Person>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    Box(modifier = modifier) {
        val listState = rememberLazyListState()
        LazyColumn(
            state = listState
        ) {
            items(persons, key = { it.id }) { person ->
                Item(
                    name = person.name,
                    photo = person.photo,
                    modifier = Modifier.clickable {
                        navigateToDetail(person.id)
                    }
                )
            }
        }
    }
}