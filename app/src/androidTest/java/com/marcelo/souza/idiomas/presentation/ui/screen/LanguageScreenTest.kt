package com.marcelo.souza.idiomas.presentation.ui.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.marcelo.souza.idiomas.presentation.ui.activity.MainActivity
import com.marcelo.souza.idiomas.utils.PORTUGUESE
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LanguageScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun topAppBarDisplaysAppNameInitially() {
        composeRule.onNodeWithTag("topAppBarComponent")
            .assertExists()
            .assertIsDisplayed()

        composeRule.onNode(hasText("Idiomas"))
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun selectingLanguageUpdatesUI() {
        composeRule.onNodeWithContentDescription("Menu")
            .performClick()

        composeRule.onNodeWithTag("menuItem_${PORTUGUESE}")
            .performClick()

        composeRule.onNodeWithTag("flagImage")
            .assertExists()
            .assertIsDisplayed()

        composeRule.onNode(hasText(PORTUGUESE))
            .assertExists()
            .assertIsDisplayed()
    }
}