package com.example.proyekcomposehantop.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyekcomposehantop.data.PersonRepository
import com.example.proyekcomposehantop.model.Person
import com.example.proyekcomposehantop.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: PersonRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Person>> =
        MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<Person>>
        get() = _uiState

    fun getPersonById(id: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getPersonById(id))
        }
    }
}