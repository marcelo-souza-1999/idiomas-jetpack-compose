package com.marcelo.souza.idiomas.presentation.ui.screen

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.marcelo.souza.idiomas.R
import com.marcelo.souza.idiomas.presentation.components.LanguageOption
import com.marcelo.souza.idiomas.presentation.components.TopAppBar
import com.marcelo.souza.idiomas.presentation.theme.AppDimensions
import com.marcelo.souza.idiomas.presentation.theme.TypographyDescription
import com.marcelo.souza.idiomas.presentation.theme.TypographyLanguage
import com.marcelo.souza.idiomas.presentation.theme.White
import com.marcelo.souza.idiomas.presentation.viewmodel.LanguagesViewModel
import com.marcelo.souza.idiomas.presentation.viewmodel.viewstate.UiState
import com.marcelo.souza.idiomas.utils.ENGLISH
import com.marcelo.souza.idiomas.utils.LOCALE_ENGLISH
import com.marcelo.souza.idiomas.utils.LOCALE_PORTUGUESE
import com.marcelo.souza.idiomas.utils.LOCALE_SPANISH
import com.marcelo.souza.idiomas.utils.PORTUGUESE
import com.marcelo.souza.idiomas.utils.SPANISH
import java.util.Locale

@Composable
fun LanguageScreen(viewModel: LanguagesViewModel) {
    val dimension = AppDimensions.dimen
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = when (uiState) {
                    is UiState.Success -> getLocalizedString(
                        context,
                        R.string.app_name,
                        (uiState as UiState.Success<String>).data
                    )
                    else -> context.getString(R.string.app_name)
                },
                showBackButton = false,
                onLanguageSelected = { languageOption ->
                    val lang = when (languageOption) {
                        is LanguageOption.Portuguese -> PORTUGUESE
                        is LanguageOption.English -> ENGLISH
                        is LanguageOption.Spanish -> SPANISH
                    }
                    viewModel.onLanguageSelected(lang)
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (uiState) {
                is UiState.Loading -> {}

                is UiState.Error -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.toast_error_get_language),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is UiState.Success -> {
                    val selectedLanguage = (uiState as UiState.Success<String>).data

                    val flagRes = when (selectedLanguage) {
                        PORTUGUESE -> R.drawable.bandeira_brasil
                        ENGLISH -> R.drawable.bandeira_eua
                        SPANISH -> R.drawable.bandeira_espanha
                        else -> R.drawable.bandeira_brasil
                    }

                    Image(
                        painter = painterResource(flagRes),
                        contentDescription = null,
                        modifier = Modifier
                            .size(dimension.size200)
                            .testTag("flagImage")
                    )

                    LocalizedText(R.string.language, selectedLanguage)
                    LocalizedText(
                        R.string.description,
                        selectedLanguage,
                        Modifier.padding(dimension.size30)
                    )
                }
            }
        }
    }
}

@Composable
private fun LocalizedText(
    @StringRes resId: Int,
    language: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Text(
        text = getLocalizedString(context, resId, language),
        style = if (resId == R.string.language) {
            TypographyLanguage.titleLarge
        } else {
            TypographyDescription.titleLarge
        },
        modifier = modifier
    )
}

private fun getLocalizedString(
    context: Context,
    @StringRes resId: Int,
    language: String
): String {
    val locale = when (language) {
        PORTUGUESE -> LOCALE_PORTUGUESE
        ENGLISH -> LOCALE_ENGLISH
        SPANISH -> LOCALE_SPANISH
        else -> Locale.getDefault()
    }
    val config = Configuration(context.resources.configuration)
    config.setLocale(locale)
    val localizedContext = context.createConfigurationContext(config)
    return localizedContext.resources.getString(resId)
}