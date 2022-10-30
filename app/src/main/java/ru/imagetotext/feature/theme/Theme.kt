package ru.imagetotext.feature.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = GreenLight,
    primaryVariant = Green,
    secondary = YellowLight,
    background = Black95,
    onBackground = White95
)

private val LightColorPalette = lightColors(
    primary = GreenDark,
    primaryVariant = Green,
    secondary = YellowDark,
    background = White95,
    onBackground = Black95

)

@Composable
fun ImageToTextTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}