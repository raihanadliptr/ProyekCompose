package com.example.proyekcomposehantop.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyekcomposehantop.data.PersonRepository
import com.example.proyekcomposehantop.model.Person
import com.example.proyekcomposehantop.ui.common.UiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: PersonRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Person>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Person>>>
    get() = _uiState

    fun getAllPersons() {
        viewModelScope.launch {
            repository.getPersons()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { persons ->
                    _uiState.value = UiState.Success(persons)
                }
        }
    }
}