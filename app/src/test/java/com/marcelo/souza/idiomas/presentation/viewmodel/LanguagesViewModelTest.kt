package com.marcelo.souza.idiomas.presentation.viewmodel

import com.marcelo.souza.idiomas.domain.repository.LocalStorageRepository
import com.marcelo.souza.idiomas.presentation.viewmodel.viewstate.UiState
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LanguagesViewModelTest {

    private lateinit var repository: LocalStorageRepository
    private lateinit var viewModel: LanguagesViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `init loads saved language successfully`() = runTest {
        every { repository.key } returns flowOf("en")

        viewModel = LanguagesViewModel(repository)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assert(state is UiState.Success)
        assertEquals("en", (state as UiState.Success).data)
    }

    @Test
    fun `init emits error when loading saved language fails`() = runTest {
        val exception = RuntimeException("fail")
        every { repository.key } returns flow { throw exception }

        viewModel = LanguagesViewModel(repository)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assert(state is UiState.Error)
        assertEquals(exception, (state as UiState.Error).throwable)
    }

    @Test
    fun `onLanguageSelected saves language successfully`() = runTest {
        coEvery { repository.saveLanguage(any()) } just Runs
        every { repository.key } returns flowOf("en")

        viewModel = LanguagesViewModel(repository)
        advanceUntilIdle()

        viewModel.onLanguageSelected("pt")
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assert(state is UiState.Success)
        assertEquals("pt", (state as UiState.Success).data)
        coVerify { repository.saveLanguage("pt") }
    }

    @Test
    fun `onLanguageSelected emits error when save fails`() = runTest {
        val exception = RuntimeException("cannot save")
        coEvery { repository.saveLanguage(any()) } throws exception
        every { repository.key } returns flowOf("en")

        viewModel = LanguagesViewModel(repository)
        advanceUntilIdle()

        viewModel.onLanguageSelected("es")
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assert(state is UiState.Error)
        assertEquals(exception, (state as UiState.Error).throwable)
    }
}