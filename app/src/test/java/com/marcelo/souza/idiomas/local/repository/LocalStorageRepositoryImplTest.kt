package com.marcelo.souza.idiomas.local.repository

import com.marcelo.souza.idiomas.domain.datasource.LocalStorageDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LocalStorageRepositoryImplTest {

    private lateinit var dataSource: LocalStorageDataSource
    private lateinit var repository: LocalStorageRepositoryImpl
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        dataSource = mockk()
        repository = LocalStorageRepositoryImpl(dataSource, testDispatcher)
    }

    @Test
    fun `key emits values from data source`() = runTest {
        val expectedValue = "en"
        every { dataSource.key } returns flow { emit(expectedValue) }

        val value = repository.key.first()

        assertEquals(expectedValue, value)
    }

    @Test
    fun `saveLanguage calls saveAnnotation on data source`() = runTest {
        val language = "pt"
        coEvery { dataSource.saveAnnotation(any()) } returns Unit

        repository.saveLanguage(language)

        coVerify(exactly = 1) { dataSource.saveAnnotation(language) }
    }
}
