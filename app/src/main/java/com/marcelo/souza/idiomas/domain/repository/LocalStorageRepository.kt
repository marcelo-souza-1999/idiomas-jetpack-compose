package com.marcelo.souza.idiomas.domain.repository

import kotlinx.coroutines.flow.Flow

interface LocalStorageRepository {

    val key: Flow<String>

    suspend fun saveLanguage(language: String)
}