package com.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.jetpackcompose.compose_path_android.ui.theme.Typography

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = Black,
    primaryContainer = PrimaryDark,
    onPrimaryContainer = Black,
    inversePrimary = PrimaryDark,
    secondary = SecondaryDark,
    onSecondary = PrimaryDark,
    secondaryContainer = SecondaryDark,
    onSecondaryContainer = Black,
    tertiary = TertiaryDark,
    onTertiary = White,
    tertiaryContainer = TertiaryDark,
    onTertiaryContainer = White,
    background = Black,
    onBackground = White,
    surface = PrimaryDark,
    onSurface = White,
    surfaceVariant = PrimaryDark,
    onSurfaceVariant = White,
    surfaceTint = White,
    inverseSurface = White,
    inverseOnSurface = Black,
    error = Red,
    onError = Black,
    errorContainer = Red,
    onErrorContainer = Black,
    outline = White,
    outlineVariant = White,
    scrim = White,
)

val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = White,
    primaryContainer = Primary,
    onPrimaryContainer = White,
    inversePrimary = Primary,
    secondary = Secondary,
    onSecondary = Primary,
    secondaryContainer = Secondary,
    onSecondaryContainer = White,
    tertiary = Tertiary,
    onTertiary = Black,
    tertiaryContainer = Tertiary,
    onTertiaryContainer = Black,
    background = White,
    onBackground = Black,
    surface = Primary,
    onSurface = White,
    surfaceVariant = Primary,
    onSurfaceVariant = White,
    surfaceTint = Black,
    inverseSurface = Black,
    inverseOnSurface = White,
    error = Red,
    onError = White,
    errorContainer = Red,
    onErrorContainer = White,
    outline = Black,
    outlineVariant = Black,
    scrim = Black
)

@Composable
fun ComposePathTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}