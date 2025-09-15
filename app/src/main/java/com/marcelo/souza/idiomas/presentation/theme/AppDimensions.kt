package com.marcelo.souza.idiomas.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.marcelo.souza.idiomas.presentation.utils.Dimensions
import com.marcelo.souza.idiomas.presentation.utils.LocalDimensions

object AppDimensions {
    val dimen: Dimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current
}