package com.marcelo.souza.idiomas.presentation.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.marcelo.souza.idiomas.presentation.theme.LanguagesTheme
import com.marcelo.souza.idiomas.utils.ENGLISH
import com.marcelo.souza.idiomas.utils.PORTUGUESE
import com.marcelo.souza.idiomas.utils.SPANISH
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TopAppBarComponentTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun topAppBar_titleDisplayed_menuOpensAndLanguageSelected() {
        var selectedLanguage = ""

        composeRule.setContent {
            LanguagesTheme {
                TopAppBar(
                    title = "Idiomas",
                    onLanguageSelected = { selectedLanguage = it.label }
                )
            }
        }

        composeRule.onNodeWithText("Idiomas").assertIsDisplayed()

        composeRule.onNodeWithContentDescription("Menu").performClick()

        composeRule.onNodeWithTag("menuItem_Português").performClick()
        assert(selectedLanguage == PORTUGUESE)

        composeRule.onNodeWithContentDescription("Menu").performClick()
        composeRule.onNodeWithTag("menuItem_Inglês").performClick()
        assert(selectedLanguage == ENGLISH)

        composeRule.onNodeWithContentDescription("Menu").performClick()
        composeRule.onNodeWithTag("menuItem_Espanhol").performClick()
        assert(selectedLanguage == SPANISH)
    }
}