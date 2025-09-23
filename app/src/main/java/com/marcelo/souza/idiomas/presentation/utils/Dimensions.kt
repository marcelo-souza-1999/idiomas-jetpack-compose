package com.marcelo.souza.idiomas.presentation.utils

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Dimensions(
    val padding8: Dp = 8.dp,
    val size2: Dp = 2.dp,
    val size30: Dp = 30.dp,
    val size200: Dp = 200.dp,
    val width200: Dp = 200.dp
)

internal val LocalDimensions = compositionLocalOf { Dimensions() }