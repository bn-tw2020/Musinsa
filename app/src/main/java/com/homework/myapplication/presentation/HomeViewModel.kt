package com.homework.myapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homework.myapplication.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _homeData = MutableStateFlow<UiState>(UiState.Empty)
    val homeData: StateFlow<UiState> = _homeData.asStateFlow()

    fun getHomeData() = viewModelScope.launch {
        _homeData.value = UiState.Loading
        repository.getHomeData().collectLatest { result ->
            result ?: return@collectLatest
            _homeData.value = UiState.Success(result)
        }
    }
}