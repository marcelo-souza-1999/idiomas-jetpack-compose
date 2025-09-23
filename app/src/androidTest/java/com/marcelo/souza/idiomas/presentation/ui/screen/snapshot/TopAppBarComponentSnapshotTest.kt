package com.marcelo.souza.idiomas.presentation.ui.screen.snapshot

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.karumi.shot.ScreenshotTest
import com.marcelo.souza.idiomas.presentation.components.TopAppBar
import com.marcelo.souza.idiomas.presentation.theme.LanguagesTheme
import org.junit.Rule
import org.junit.Test

class TopAppBarComponentSnapshotTest : ScreenshotTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun topAppBar_defaultState_snapshot() {
        composeTestRule.setContent {
            LanguagesTheme {
                TopAppBar(title = "Home")
            }
        }

        compareScreenshot(composeTestRule, name = "top_app_bar_default")
    }

    @Test
    fun topAppBar_withBackButtonAndCloseButton_snapshot() {
        composeTestRule.setContent {
            LanguagesTheme {
                TopAppBar(
                    title = "Detalhes",
                    showBackButton = true,
                    onBackClicked = { },
                    onCloseClicked = { }
                )
            }
        }

        compareScreenshot(composeTestRule, name = "top_app_bar_with_buttons")
    }

    @Test
    fun topAppBar_withOpenMenu_snapshot() {
        composeTestRule.setContent {
            LanguagesTheme {
                TopAppBar(title = "Settings")
            }
        }

        composeTestRule.onNodeWithContentDescription("Menu").performClick()

        compareScreenshot(
            composeTestRule.onNodeWithTag("dropdownMenu"),
            name = "top_app_bar_with_open_menu"
        )
    }
}