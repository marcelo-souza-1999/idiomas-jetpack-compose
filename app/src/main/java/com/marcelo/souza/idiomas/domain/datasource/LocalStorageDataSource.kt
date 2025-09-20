package com.marcelo.souza.idiomas.domain.datasource

import kotlinx.coroutines.flow.Flow

interface LocalStorageDataSource {

    val key: Flow<String>

    suspend fun saveAnnotation(annotation: String)
}