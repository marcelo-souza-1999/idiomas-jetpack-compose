package com.marcelo.souza.idiomas.presentation.ui.screen.snapshot

import androidx.compose.ui.test.junit4.createComposeRule
import com.karumi.shot.ScreenshotTest
import com.marcelo.souza.idiomas.presentation.ui.screen.LanguageScreen
import com.marcelo.souza.idiomas.presentation.viewmodel.LanguagesViewModel
import com.marcelo.souza.idiomas.presentation.viewmodel.viewstate.UiState
import com.marcelo.souza.idiomas.utils.ENGLISH
import com.marcelo.souza.idiomas.utils.PORTUGUESE
import com.marcelo.souza.idiomas.utils.SPANISH
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test

class LanguageScreenSnapshotTest : ScreenshotTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun createMockViewModel(language: String): LanguagesViewModel {
        val viewModel = mockk<LanguagesViewModel>()
        val uiStateFlow = MutableStateFlow<UiState<String>>(UiState.Success(language))
        every { viewModel.uiState } returns uiStateFlow
        return viewModel
    }

    @Test
    fun languageScreen_portuguese_snapshot() {
        val viewModel = createMockViewModel(PORTUGUESE)
        composeTestRule.setContent { LanguageScreen(viewModel) }
        compareScreenshot(composeTestRule, name = "language_screen_portuguese")
    }

    @Test
    fun languageScreen_english_snapshot() {
        val viewModel = createMockViewModel(ENGLISH)
        composeTestRule.setContent { LanguageScreen(viewModel) }
        compareScreenshot(composeTestRule, name = "language_screen_english")
    }

    @Test
    fun languageScreen_spanish_snapshot() {
        val viewModel = createMockViewModel(SPANISH)
        composeTestRule.setContent { LanguageScreen(viewModel) }
        compareScreenshot(composeTestRule, name = "language_screen_spanish")
    }
}
