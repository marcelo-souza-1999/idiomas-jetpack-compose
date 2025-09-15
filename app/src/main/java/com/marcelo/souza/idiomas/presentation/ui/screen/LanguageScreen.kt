package com.marcelo.souza.idiomas.presentation.ui.screen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.marcelo.souza.idiomas.R
import com.marcelo.souza.idiomas.presentation.components.TopAppBar
import com.marcelo.souza.idiomas.presentation.theme.AppDimensions
import com.marcelo.souza.idiomas.presentation.theme.LanguagesTheme
import com.marcelo.souza.idiomas.presentation.theme.TypographyDescription
import com.marcelo.souza.idiomas.presentation.theme.TypographyLanguage
import com.marcelo.souza.idiomas.presentation.theme.White

@Composable
fun LanguageScreen() {
    val dimension = AppDimensions.dimen

    Scaffold(
        topBar = {
            TopAppBar()
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
            Image(
                painter = painterResource(R.drawable.bandeira_brasil),
                contentDescription = null,
                modifier = Modifier
                    .size(dimension.size200)
            )

            Text(
                text = stringResource(R.string.language),
                style = TypographyLanguage.titleLarge
            )

            Text(
                text = stringResource(R.string.description),
                style = TypographyDescription.titleLarge,
                modifier = Modifier
                    .padding(dimension.size30)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = false
)
@Composable
internal fun PreviewLanguageScreen() {
    LanguagesTheme {
        LanguageScreen()
    }
}