package com.marcelo.souza.idiomas.local.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.marcelo.souza.idiomas.domain.datasource.LocalStorageDataSource
import com.marcelo.souza.idiomas.utils.EMPTY_STRING
import com.marcelo.souza.idiomas.utils.LANGUAGE_ORDER_BY_KEY
import com.marcelo.souza.idiomas.utils.LOCAL_DATASTORE_NAME
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

@Single
class LocalStorageDataSourceImpl(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LocalStorageDataSource {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = LOCAL_DATASTORE_NAME
    )

    private val annotationKey = stringPreferencesKey(LANGUAGE_ORDER_BY_KEY)

    override val key: Flow<String>
        get() = context.dataStore.data
            .map { localStorage ->
                localStorage[annotationKey] ?: EMPTY_STRING
            }.flowOn(ioDispatcher)

    override suspend fun saveAnnotation(annotation: String) {
        context.dataStore.edit { localStorage ->
            localStorage[annotationKey] = annotation
        }
    }
}