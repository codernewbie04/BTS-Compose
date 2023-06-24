package com.akmal.bandungcompose.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akmal.bandungcompose.BandungRepository
import com.akmal.bandungcompose.model.TempatWisata
import com.akmal.bandungcompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel (
    private val repository: BandungRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<TempatWisata>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<TempatWisata>>>
        get() = _uiState

    fun getAllDestination() {
        viewModelScope.launch {
            repository.getAllDestination()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect {data ->
                    _uiState.value = UiState.Success(data)

                }
        }
    }
}