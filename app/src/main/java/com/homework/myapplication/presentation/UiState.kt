package com.homework.myapplication.presentation

import com.homework.myapplication.data.model.ListResponse

sealed class UiState {
    object Empty : UiState()
    object Error : UiState()
    object Loading : UiState()
    data class Success(val list: ListResponse) : UiState()
}
