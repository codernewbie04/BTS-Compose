package com.akmal.bandungcompose.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akmal.bandungcompose.BandungRepository
import com.akmal.bandungcompose.model.TempatWisata
import com.akmal.bandungcompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: BandungRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<TempatWisata>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<TempatWisata>>
        get() = _uiState

    fun getIdDestination(idDestination: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getDestination(idDestination))
        }
    }
}