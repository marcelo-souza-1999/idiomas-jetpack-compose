package com.marcelo.souza.idiomas.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.marcelo.souza.idiomas.R
import com.marcelo.souza.idiomas.presentation.theme.AppDimensions
import com.marcelo.souza.idiomas.presentation.theme.Blue900
import com.marcelo.souza.idiomas.presentation.theme.LanguagesTheme
import com.marcelo.souza.idiomas.presentation.theme.TypographyTitle
import com.marcelo.souza.idiomas.presentation.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    title: String = "",
    showBackButton: Boolean = false,
    onBackClicked: (() -> Unit)? = null,
    onCloseClicked: (() -> Unit)? = null,
    onLanguageSelected: (LanguageOption) -> Unit = { }
) {
    val dimension = AppDimensions.dimen
    var isMenuOpen by remember { mutableStateOf(false) }

    TopAppBar(
        modifier = Modifier.testTag("topAppBarComponent"),
        title = {
            Text(
                text = title,
                modifier = Modifier
                    .padding(start = dimension.padding8),
                style = TypographyTitle.titleLarge
            )
        },
        navigationIcon = {
            if (showBackButton && onBackClicked != null) {
                IconButton(onClick = onBackClicked) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = White
                    )
                }
            }
        },
        actions = {
            onCloseClicked?.let {
                IconButton(onClick = it) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = White
                    )
                }
            }

            IconButton(
                onClick = { isMenuOpen = true }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    tint = White,
                    contentDescription = null
                )
            }

            DropdownMenu(
                expanded = isMenuOpen,
                onDismissRequest = { isMenuOpen = false },
                modifier = Modifier.width(dimension.width200)
            ) {
                LanguageOption.all.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option.label) },
                        leadingIcon = {
                            Image(
                                painter = painterResource(option.iconRes),
                                contentDescription = option.label,
                                modifier = Modifier.size(dimension.size30)
                            )
                        },
                        onClick = {
                            onLanguageSelected(option)
                            isMenuOpen = false
                        }
                    )
                }
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = Blue900,
            titleContentColor = White
        )
    )
}

sealed class LanguageOption(
    val label: String,
    val iconRes: Int
) {
    object Portuguese : LanguageOption("Português", R.drawable.brasil)
    object English : LanguageOption("Inglês", R.drawable.eua)
    object Spanish : LanguageOption("Espanhol", R.drawable.espanha)

    companion object {
        val all = listOf(Portuguese, English, Spanish)
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
internal fun PreviewTopAppBar() {
    LanguagesTheme {
        TopAppBar(
            title = "Idiomas",
            showBackButton = false,
            onBackClicked = { })
    }
}