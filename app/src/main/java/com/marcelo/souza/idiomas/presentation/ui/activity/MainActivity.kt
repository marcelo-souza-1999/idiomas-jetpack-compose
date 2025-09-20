package com.marcelo.souza.idiomas.presentation.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.marcelo.souza.idiomas.presentation.theme.LanguagesTheme
import com.marcelo.souza.idiomas.presentation.ui.screen.LanguageScreen
import com.marcelo.souza.idiomas.presentation.viewmodel.LanguagesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: LanguagesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        enableEdgeToEdge()
        setContent {
            LanguagesTheme {
                LanguageScreen(
                    viewModel
                )
            }
        }
    }
}