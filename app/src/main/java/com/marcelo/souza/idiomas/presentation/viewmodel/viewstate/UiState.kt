package com.marcelo.souza.idiomas.presentation.viewmodel.viewstate

sealed interface UiState<out T> {
    object Loading : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val throwable: Throwable, val message: String? = null) : UiState<Nothing>
}