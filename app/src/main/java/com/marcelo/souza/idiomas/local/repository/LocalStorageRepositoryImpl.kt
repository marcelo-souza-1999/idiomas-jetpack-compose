package com.marcelo.souza.idiomas.local.repository

import com.marcelo.souza.idiomas.domain.datasource.LocalStorageDataSource
import com.marcelo.souza.idiomas.domain.repository.LocalStorageRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Single

@Single
class LocalStorageRepositoryImpl(
    private val localStorageDataSource: LocalStorageDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LocalStorageRepository {

    override val key: Flow<String>
        get() = localStorageDataSource.key
            .flowOn(ioDispatcher)

    override suspend fun saveLanguage(language: String) =
        localStorageDataSource.saveAnnotation(language)
}