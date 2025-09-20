package com.marcelo.souza.idiomas.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcelo.souza.idiomas.domain.repository.LocalStorageRepository
import com.marcelo.souza.idiomas.presentation.viewmodel.viewstate.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LanguagesViewModel(
    private val localStorageRepository: LocalStorageRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<String>>(UiState.Loading)
    val uiState: StateFlow<UiState<String>> = _uiState

    init {
        loadSavedLanguage()
    }

    fun onLanguageSelected(language: String) = viewModelScope.launch {
        _uiState.value = UiState.Loading

        runCatching {
            localStorageRepository.saveLanguage(language)
        }
            .onSuccess {
                _uiState.value = UiState.Success(language)
            }
            .onFailure { error ->
                _uiState.value = UiState.Error(error, error.message)
            }
    }

    private fun loadSavedLanguage() = viewModelScope.launch {
        localStorageRepository.key
            .onStart { _uiState.value = UiState.Loading }
            .catch { throwable ->
                _uiState.value = UiState.Error(throwable, throwable.message)
            }
            .collect { savedLanguage ->
                _uiState.value = UiState.Success(savedLanguage)
            }
    }
}