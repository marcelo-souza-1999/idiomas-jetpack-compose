package com.marcelo.souza.idiomas.local.datasource

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.marcelo.souza.idiomas.utils.EMPTY_STRING
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
class LocalStorageDataSourceImplTest {

    private lateinit var context: Context
    private lateinit var dataSource: LocalStorageDataSourceImpl
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        dataSource = LocalStorageDataSourceImpl(context, testDispatcher)
    }

    @Test
    fun `key emits EMPTY_STRING initially`() = runTest {
        val value = dataSource.key.first()
        assertEquals(EMPTY_STRING, value)
    }

    @Test
    fun `saveAnnotation stores value and key emits it`() = runTest {
        dataSource.saveAnnotation("pt")
        val value = dataSource.key.first()
        assertEquals("pt", value)
    }

    @Test
    fun `saveAnnotation overwrites value`() = runTest {
        dataSource.saveAnnotation("en")
        dataSource.saveAnnotation("es")
        val value = dataSource.key.first()
        assertEquals("es", value)
    }
}